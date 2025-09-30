using backend.Core.IO;
using backend.Domain.Airports.Entity;

namespace backend.Domain.Airports.IO;

public class AirportCsvReader
{
    private const string AirportPath = "backend.Resources.airports.csv";

    public List<Airport> Read()
    {
        var lines = CsvReader.ReadLines(AirportPath);
        return lines
            .Select(line => new Airport(
                line[0], line[1], Double.Parse(line[2]), 
                Double.Parse(line[3]), line[4],
                line[5], line[6], Double.Parse(line[7])))
            .ToList();
    }
}