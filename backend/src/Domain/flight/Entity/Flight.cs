using System.ComponentModel.DataAnnotations.Schema;
using backend.Core.Entity;
using backend.Domain.fleet.Entity;
using backend.Domain.flight.Enum;

namespace backend.Domain.flight.Entity;

public class Flight : BaseEntity
{
    public FlightStatus FlightStatus { get; set; }
    public Plane Plane { get; set; }
    public FlightPlan FlightPlan { get; set; }
    public DateTime BoardingTime { get; set; }
    public DateTime TakeoffTime { get; set; }
    [NotMapped]
    public DateTime ExpectedArrivalTime => CalculateArrivalTime();

    private DateTime CalculateArrivalTime()
    {
        return TakeoffTime.Add(FlightPlan.FlightDuration);
    }
}