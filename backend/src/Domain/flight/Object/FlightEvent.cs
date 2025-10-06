using backend.Core.Object;

namespace backend.Domain.flight.Object;

public class FlightEvent : Event
{
    public long FlightId { get; set; }
}