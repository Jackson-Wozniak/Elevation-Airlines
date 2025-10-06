using backend.Core.Infrastructure.EventQueue;

namespace backend.Domain.flight.Object;

public class FlightEvent : Event
{
    public long FlightId { get; set; }
}