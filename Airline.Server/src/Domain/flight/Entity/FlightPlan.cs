using System.ComponentModel.DataAnnotations.Schema;
using Airline.Server.Core.Entity;
using Route = Airline.Server.Domain.shared.Route;

namespace Airline.Server.Domain.flight.Entity;

public class FlightPlan : BaseEntity
{
    public Flight Flight { get; set; }
    public Route Route { get; set; }
    
    public FlightPlan() { }

    public FlightPlan(Flight flight, Route route)
    {
        Flight = flight;
        Route = new Route(route.Departure, route.Destination);
    }

    public TimeSpan CalculateFlightDuration(double averageKnots)
    {
        return TimeSpan.FromHours(Route.DistanceNauticalMiles / averageKnots); 
    }
    
}