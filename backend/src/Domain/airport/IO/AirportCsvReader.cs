using backend.Core.IO;
using backend.Core.Utils;
using backend.Domain.airport.Entity;

namespace backend.Domain.airport.IO;

public class AirportCsvReader
{
    private const string AirportPath = "backend.Resources.airports.csv";

    public static List<Airport> Read()
    {
        var lines = CsvReader.ReadLines(AirportPath);
        var airports = new List<Airport>();
        foreach (var line in lines)
        {
            var code = line[0];
            var name = line[2];
            var lat = line[3].GetDoubleOrDefault(-10000.0);
            var longitude = line[4].GetDoubleOrDefault(-10000.0);
            var country = line[5];
            var state = line[6];
            var city = line[7];
            var percentile = line[8].GetDoubleOrDefault(-10000.0);
            if ((int)percentile == -10000 || (int)lat == 10000 || (int)longitude == 10000)
            {
                throw new IOException($"Error reading airport for initialization: {code}");
            }
            airports.Add(new Airport(code, name, lat, longitude, 
                city, state, country, percentile));
        }
        return airports;
    }
}