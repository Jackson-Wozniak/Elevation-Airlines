using backend.Core.IO;
using backend.Core.Utils;
using backend.Domain.aircraft.Entity;
using backend.Domain.aircraft.Enums;

namespace backend.Domain.aircraft.IO;

public class AircraftCsvReader
{
    private const string AircraftPath = "backend.Resources.planes.csv";

    public static List<Aircraft> Read()
    {
        var lines = CsvReader.ReadLines(AircraftPath);
        var aircraft = new List<Aircraft>();
        foreach (var line in lines)
        {
            var make = ManufacturerTypeUtils
                .FromName(line[0].Replace("_", ""));
            var model = line[1];
            var speed = line[2].GetIntOrDefault(-1);
            var range = line[3].GetIntOrDefault(-1);
            var passengers = line[4].GetIntOrDefault(-1);
            var category = AircraftCategoryTypeUtils
                .FromName(line[5].Replace("_", ""));
            
            if (speed == -1 || range == 1 || passengers == 1 
                || make == null || category == null)
            {
                throw new IOException($"Error reading airport for initialization: {model}");
            }
            aircraft.Add(new Aircraft(make.GetValueOrDefault(), model, 
                speed, range, passengers, category.GetValueOrDefault()));
        }
        return aircraft;
    }
}