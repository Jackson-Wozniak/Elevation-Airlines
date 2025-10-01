using backend.Core.Entity;
using Route = backend.Domain.shared.Route;

namespace backend.Domain.flight.Entity;

public class FlightPlan : BaseEntity
{
    public Flight Flight { get; set; }
    public Route Route { get; set; }
}