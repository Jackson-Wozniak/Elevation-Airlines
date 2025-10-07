using System.ComponentModel.DataAnnotations.Schema;
using backend.Core.Entity;
using backend.Domain.fleet.Entity;
using backend.Domain.flight.Enum;
using backend.Domain.flight.Object;
using Route = backend.Domain.shared.Route;

namespace backend.Domain.flight.Entity;

public class Flight : BaseEntity
{
    public FlightStatus FlightStatus { get; set; }
    public Plane Plane { get; set; }
    public FlightPlan FlightPlan { get; set; }
    public DateTime BoardingTime { get; set; }
    public DateTime TakeoffTime { get; set; }
    public DateTime ExpectedArrivalTime { get; set; }

    protected Flight()
    {
        
    }

    public static Flight Create(Route route, Plane plane, DateTime boarding, DateTime takeoff)
    {
        var flight = new Flight();
        flight.BoardingTime = boarding;
        flight.FlightStatus = FlightStatus.Scheduled;
        flight.TakeoffTime = takeoff;
        flight.Plane = plane;
        flight.FlightPlan = new FlightPlan(flight, route);
        flight.ExpectedArrivalTime = flight.TakeoffTime.Add(
            flight.FlightPlan.CalculateFlightDuration(
                flight.Plane.Aircraft.CruiseSpeedKnots * .8));
        return flight;
    }

    public bool StartsOnDate(DateOnly date)
    {
        return DateOnly.FromDateTime(BoardingTime).Equals(date);
    }

    public bool StartsOnDate(DateTime date)
    {
        return StartsOnDate(DateOnly.FromDateTime(date));
    }

    public List<FlightEvent> CreateEvents()
    {
        return
        [
            new FlightEvent(Id, FlightEventType.StartBoarding, BoardingTime),
            new FlightEvent(Id, FlightEventType.Takeoff, TakeoffTime),
            new FlightEvent(Id, FlightEventType.Completed, ExpectedArrivalTime),
        ];
    }
}