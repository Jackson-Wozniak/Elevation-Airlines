using backend.Core.Data;
using backend.Core.Exception.types;
using backend.Domain.flight.Entity;
using Microsoft.EntityFrameworkCore;

namespace backend.Domain.flight.Repository;

public class FlightRepository(ApplicationDbContext context)
{
    public List<Flight> GetAll()
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

    public Flight GetById(long id)
    {
        var flight = GetAll().SingleOrDefault(f => f.Id == id);

        if (flight == null) throw new NotFoundException($"No flight: {id}", "FlightService.GetFlight");
        return flight;
    }
    
    public void DeleteAll()
    {
        context.Flights.RemoveRange(context.Flights);
        context.SaveChanges();
    }

    public List<Flight> SaveAll(List<Flight> flights)
    {
        context.Flights.AddRange(flights);
        context.SaveChanges();
        return flights;
    }
    
    public Flight Save(Flight flight)
    {
        context.Flights.Add(flight);
        context.SaveChanges();
        return flight;
    }

    public void Update(Flight flight)
    {
        context.Flights.Update(flight);
        context.SaveChanges();
    }
}