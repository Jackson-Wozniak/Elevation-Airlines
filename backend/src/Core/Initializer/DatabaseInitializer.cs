using backend.Core.Interface;
using backend.Domain.Aircrafts.IO;
using backend.Domain.Airports.IO;

namespace backend.Core.Initializer;

public class DatabaseInitializer : IInitializer
{
    public void Initialize()
    {
        Console.WriteLine(AirportCsvReader.Read().Count);
        Console.WriteLine(AircraftCsvReader.Read().Count);
    }
}