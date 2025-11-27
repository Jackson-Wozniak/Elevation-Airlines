using Airline.Server.Core.Interface;
using Airline.Server.Engine.Settings;
using Airline.Server.Domain.fleet.Repository;
using Airline.Server.Domain.fleet.Service;
using Airline.Server.Domain.flight.Service;
using Airline.Server.Domain.routenetwork.Service;
using Airline.Server.Engine.Interface;
using Airline.Server.Engine.Service;
using Microsoft.Extensions.Options;

namespace Airline.Server.Engine.Initializer;

public class AirlineInitializer(
    PlaneRepository planeRepository,
    FlightService flightService,
    NetworkedRouteService networkedRouteService,
    INetworkPlanService networkPlanService,
    IFlightSchedulerService flightSchedulerService,
    FleetService fleetService,
    FlightEventService flightEventService,
    FlightPublisherService flightPublisherService,
    ILogger<DatabaseInitializer> logger,
    IOptions<SimulationSettings> simulationOptions,
    IOptions<InitializerSettings> initializerOptions) : IInitializer
{
    private readonly SimulationSettings _simulationSettings = simulationOptions.Value;
    private readonly InitializerSettings _initializerSettings = initializerOptions.Value;
    
    public void Initialize()
    {
        if (_initializerSettings.ResetSimulationState || _initializerSettings.ResetStaticData)
        {
            if (!_initializerSettings.ResetStaticData)
            {
                logger.LogInformation("Clearing previous simulation state for (Flight, Plane, NetworkedRoute");
                flightService.DeleteAllFlights();
                planeRepository.DeleteAllPlanes();
                networkedRouteService.DeleteAllNetworkedRoutes();
            }
            logger.LogInformation("Initializing simulation state: PrimaryHub({%s}), FleetSize({%d})", _simulationSettings.PrimaryHub, _simulationSettings.FleetSize);
            InitializeFleet();
            InitializeRouteNetwork();
            InitializeFlightSchedule();
        }
        InitializeFlightEventQueue();
    }

    private void InitializeRouteNetwork()
    {
        networkPlanService.CreateRouteNetwork();
    }

    private void InitializeFleet()
    {
        fleetService.CreateFleet();
    }

    private void InitializeFlightSchedule()
    {
        logger.LogInformation("Scheduling flights for following 7 days");
        var today = DateOnly.FromDateTime(DateTime.Today);
        var scheduleStart = today.AddDays(1);
        var scheduleEnd = scheduleStart.AddDays(6);
        var flights = flightSchedulerService.ScheduleAndSave(scheduleStart, scheduleEnd);
        flightPublisherService.PublishScheduledFlight(flights);
    }

    private void InitializeFlightEventQueue()
    {
        logger.LogInformation("Queuing flight events for following 2 days");
        var today = DateOnly.FromDateTime(DateTime.Today);
        flightEventService.QueueEvents(today.AddDays(1));
        flightEventService.QueueEvents(today.AddDays(2));
    }
}