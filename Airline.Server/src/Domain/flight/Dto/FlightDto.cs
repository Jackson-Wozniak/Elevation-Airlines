using Airline.Server.Domain.fleet.Dto;
using Airline.Server.Domain.flight.Entity;

namespace Airline.Server.Domain.flight.Dto;

public class FlightDto
{
    public string Status { get; set; }
    public PlaneDto Plane { get; set; }
    public FlightPlanDto FlightPlan { get; set; }
    public DateTime BoardingTime { get; set; }
    public DateTime TakeoffTime { get; set; }
    public DateTime ExpectedArrivalTime { get; set; }

    public FlightDto(Flight flight)
    {
        Status = flight.FlightStatus.ToString();
        Plane = new PlaneDto(flight.Plane);
        FlightPlan = new FlightPlanDto(flight.FlightPlan);
        BoardingTime = flight.BoardingTime;
        TakeoffTime = flight.TakeoffTime;
        ExpectedArrivalTime = flight.ExpectedArrivalTime;
    }
}