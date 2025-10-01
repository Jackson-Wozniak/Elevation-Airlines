using backend.Core.Interface;
using backend.Domain.Airports.IO;

namespace backend.Core.Initializer;

public class DatabaseInitializer : IInitializer
{
    public void Initialize()
    {
        Console.Write(AirportCsvReader.Read().Count);
    }
}