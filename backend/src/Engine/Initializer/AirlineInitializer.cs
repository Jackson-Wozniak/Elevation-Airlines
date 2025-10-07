using backend.Core.Interface;
using backend.Core.Settings;
using backend.Domain.fleet.Service;
using backend.Domain.flight.Service;
using backend.Domain.routenetwork.Service;
using backend.Engine.Interface;
using backend.Engine.Service;
using Microsoft.Extensions.Options;

namespace backend.Engine.Initializer;

public class AirlineInitializer(
    PlaneService planeService,
    FlightService flightService,
    NetworkedRouteService networkedRouteService,
    INetworkPlanService networkPlanService,
    IFlightSchedulerService flightSchedulerService,
    FleetService fleetService,
    FlightEventService flightEventService,
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
                planeService.DeleteAllPlanes();
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
        flightSchedulerService.ScheduleAndSave(scheduleStart, scheduleEnd);
    }

    private void InitializeFlightEventQueue()
    {
        logger.LogInformation("Queuing flight events for following 2 days");
        var today = DateOnly.FromDateTime(DateTime.Today);
        flightEventService.QueueEvents(today.AddDays(1));
        flightEventService.QueueEvents(today.AddDays(2));
    }
}