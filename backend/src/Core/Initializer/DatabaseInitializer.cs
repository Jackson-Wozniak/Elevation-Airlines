using backend.Core.Data;
using backend.Core.Interface;
using backend.Core.Settings;
using backend.Domain.Aircrafts.IO;
using backend.Domain.Airports.IO;
using Microsoft.Extensions.Options;

namespace backend.Core.Initializer;

public class DatabaseInitializer(ApplicationDbContext context, IOptions<InitializerSettings> settings) : IInitializer
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
        context.Aircraft.AddRange(AircraftCsvReader.Read());
        context.SaveChanges();
    }

    private void ResetAirports()
    {
        context.Airports.AddRange(AirportCsvReader.Read());
        context.SaveChanges();
    }
}