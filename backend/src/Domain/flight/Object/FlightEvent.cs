using backend.Core.Infrastructure.EventQueue;
using backend.Domain.flight.Enum;

namespace backend.Domain.flight.Object;

public class FlightEvent : Event
{
    public long FlightId { get; set; }
    public FlightEventType EventType { get; set; }

    public FlightEvent(long id, FlightEventType type, DateTime time)
    {
        FlightId = id;
        EventType = type;
        ScheduledTime = time;
    }
}