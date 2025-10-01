using backend.Core.Interface;
using backend.Core.Settings;
using backend.Domain.aircraft.Service;
using backend.Domain.airport.Service;
using backend.Domain.fleet.Service;
using backend.Domain.flight.Service;
using backend.Domain.routenetwork.Service;
using Microsoft.Extensions.Options;

namespace backend.Core.Initializer;

public class AirlineInitializer(
    PlaneService planeService,
    FlightService flightService,
    NetworkedRouteService networkedRouteService,
    ILogger<DatabaseInitializer> logger,
    IOptions<SimulationSettings> simulationOptions,
    IOptions<InitializerSettings> initializerOptions) : IInitializer
{
    private readonly SimulationSettings _simulationSettings = simulationOptions.Value;
    private readonly InitializerSettings _initializerSettings = initializerOptions.Value;
    
    public void Initialize()
    {
        if (_initializerSettings.ResetSimulationState || _initializerSettings.ResetStaticDataOnStartup)
        {
            if (!_initializerSettings.ResetStaticDataOnStartup)
            {
                logger.LogInformation("Clearing previous simulation state for (Flight, Plane, NetworkedRoute");
                flightService.DeleteAllFlights();
                planeService.DeleteAllPlanes();
                networkedRouteService.DeleteAllNetworkedRoutes();
            }
            logger.LogInformation("Initializing simulation state: PrimaryHub({%s}), FleetSize({%d})", _simulationSettings.PrimaryHub, _simulationSettings.FleetSize);
            InitializeRouteNetwork();
            InitializeFleet();
            InitializeFlightSchedule();
        }
    }

    private void InitializeRouteNetwork()
    {
        
    }

    private void InitializeFleet()
    {
        
    }

    private void InitializeFlightSchedule()
    {
        
    }
}