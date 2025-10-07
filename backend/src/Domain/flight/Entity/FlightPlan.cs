using System.ComponentModel.DataAnnotations.Schema;
using backend.Core.Entity;
using Route = backend.Domain.shared.Route;

namespace backend.Domain.flight.Entity;

public class FlightPlan : BaseEntity
{
    public Flight Flight { get; set; }
    public Route Route { get; set; }
    [NotMapped] 
    public TimeSpan FlightDuration => CalculateFlightDuration();
    
    public FlightPlan() { }

    public FlightPlan(Flight flight, Route route)
    {
        Flight = flight;
        Route = new Route(route.Departure, route.Destination);
    }

    private TimeSpan CalculateFlightDuration()
    {
        return TimeSpan.FromHours(1);
    }
    
}