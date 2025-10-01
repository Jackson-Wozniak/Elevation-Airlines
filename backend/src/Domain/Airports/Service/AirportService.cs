using backend.Core.Data;
using backend.Domain.Airports.Entity;

namespace backend.Domain.Airports.Service;

public class AirportService(ApplicationDbContext context)
{
    public void DeleteAllAirports()
    {
        context.Airports.RemoveRange(context.Airports);
        context.SaveChanges();
    }

    public void SaveAirport(List<Airport> aircraft)
    {
        context.Airports.AddRange(aircraft);
        context.SaveChanges();
    }
}