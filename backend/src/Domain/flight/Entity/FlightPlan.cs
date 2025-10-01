using backend.Core.Entity;

namespace backend.Domain.flight.Entity;

public class FlightPlan : BaseEntity
{
    public Flight Flight { get; set; }
}