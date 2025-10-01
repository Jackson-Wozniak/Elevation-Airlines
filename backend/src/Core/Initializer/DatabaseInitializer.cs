using backend.Core.Interface;
using backend.Core.Settings;
using backend.Domain.aircraft.IO;
using backend.Domain.aircraft.Service;
using backend.Domain.airport.IO;
using backend.Domain.airport.Service;
using backend.Domain.fleet.Service;
using backend.Domain.flight.Service;
using backend.Domain.routenetwork.Service;
using Microsoft.Extensions.Options;

namespace backend.Core.Initializer;

public class DatabaseInitializer(
    AirportService airportService,
    AircraftService aircraftService,
    PlaneService planeService,
    FlightService flightService,
    NetworkedRouteService networkedRouteService,
    FleetService fleetService,
    ILogger<DatabaseInitializer> logger,
    IOptions<InitializerSettings> settings) : IInitializer
{
    private readonly InitializerSettings _settings = settings.Value;
    
    public void Initialize()
    {
        if (_settings.ResetStaticDataOnStartup)
        {
            logger.LogInformation("Resetting static data: {%s}", string.Join(", ", _settings.ResetStaticDataTypes));
            if(!_settings.ResetSimulationState) logger.LogDebug("ResetSimulationState is OFF, but ResetStaticDataOnStartup is ON. To avoid resetting simulation state, turn ResetStaticDataOnStartup OFF");
            
            planeService.DeleteAllPlanes();
            networkedRouteService.DeleteAllNetworkedRoutes();
            flightService.DeleteAllFlights();
            
            if(_settings.ResetStaticDataTypes.Contains("Airport")) ResetAirports();
            if(_settings.ResetStaticDataTypes.Contains("Aircraft")) ResetAircraft();
        }
    }

    private void ResetAircraft()
    {
        aircraftService.DeleteAllAircraft();
        aircraftService.SaveAircraft(AircraftCsvReader.Read());
    }

    private void ResetAirports()
    {
        airportService.DeleteAllAirports();
        airportService.SaveAirport(AirportCsvReader.Read());
    }
}