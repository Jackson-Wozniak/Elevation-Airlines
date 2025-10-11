using Airline.Server.Core.Interface;
using Airline.Server.Core.Settings;
using Airline.Server.Domain.aircraft.IO;
using Airline.Server.Domain.aircraft.Service;
using Airline.Server.Domain.airport.IO;
using Airline.Server.Domain.airport.Service;
using Airline.Server.Domain.fleet.Repository;
using Airline.Server.Domain.fleet.Service;
using Airline.Server.Domain.flight.Service;
using Airline.Server.Domain.routenetwork.Service;
using Microsoft.Extensions.Options;

namespace Airline.Server.Engine.Initializer;

public class DatabaseInitializer(
    AirportService airportService,
    AircraftService aircraftService,
    PlaneRepository planeRepository,
    FlightService flightService,
    NetworkedRouteService networkedRouteService,
    ILogger<DatabaseInitializer> logger,
    IOptions<InitializerSettings> settings) : IInitializer
{
    private readonly InitializerSettings _settings = settings.Value;
    
    public void Initialize()
    {
        if (_settings.ResetStaticData)
        {
            logger.LogInformation("Resetting static data: {%s}", string.Join(", ", _settings.ResetStaticDataTypes));
            if(!_settings.ResetSimulationState) logger.LogDebug("ResetSimulationState is OFF, but ResetStaticDataOnStartup is ON. To avoid resetting simulation state, turn ResetStaticDataOnStartup OFF");
            
            planeRepository.DeleteAllPlanes();
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