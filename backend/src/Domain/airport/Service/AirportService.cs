using backend.Core.Data;
using backend.Domain.airport.Entity;

namespace backend.Domain.airport.Service;

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