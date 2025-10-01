using backend.Core.Interface;
using backend.Core.Settings;
using backend.Domain.aircraft.IO;
using backend.Domain.aircraft.Service;
using backend.Domain.airport.IO;
using backend.Domain.airport.Service;
using Microsoft.Extensions.Options;

namespace backend.Core.Initializer;

public class DatabaseInitializer(
    AirportService airportService,
    AircraftService aircraftService,
    IOptions<InitializerSettings> settings) : IInitializer
{
    private readonly InitializerSettings _settings = settings.Value;
    
    public void Initialize()
    {
        if (_settings.ResetStaticDataOnStartup)
        {
            //TODO: if resetting static data we'll need to clear simulation tables
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