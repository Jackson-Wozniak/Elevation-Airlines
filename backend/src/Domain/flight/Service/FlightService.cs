using backend.Core.Data;
using backend.Core.Exception.types;
using backend.Domain.flight.Entity;
using Microsoft.EntityFrameworkCore;

namespace backend.Domain.flight.Service;

public class FlightService(ApplicationDbContext context)
{
    public List<Flight> GetFlights()
    {
        return context.Flights
            .Include(f => f.FlightPlan)
                .ThenInclude(plan => plan.Route.Departure)
            .Include(f => f.FlightPlan)
                .ThenInclude(plan => plan.Route.Destination)
            .Include(f => f.Plane)
                .ThenInclude(plane => plane.Aircraft)
            .ToList();
    }

    public Flight GetFlight(long id)
    {
        var flight = GetFlights().SingleOrDefault(f => f.Id == id);

        if (flight == null) throw new NotFoundException($"No flight: {id}", "FlightService.GetFlight");
        return flight;
    }
    
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