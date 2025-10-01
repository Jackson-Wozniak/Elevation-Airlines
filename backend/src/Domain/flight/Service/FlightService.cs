using backend.Core.Data;
using backend.Domain.flight.Entity;

namespace backend.Domain.flight.Service;

public class FlightService(ApplicationDbContext context)
{
    public void DeleteAllFlights()
    {
        context.Flights.RemoveRange(context.Flights);
        context.SaveChanges();
    }

    public void SaveFlight(List<Flight> flights)
    {
        context.Flights.AddRange(flights);
        context.SaveChanges();
    }
    
    public void SaveFlight(Flight flight)
    {
        context.Flights.Add(flight);
        context.SaveChanges();
    }
}