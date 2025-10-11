using Airline.Server.Core.Infrastructure.EventQueue;
using Airline.Server.Domain.flight.Enum;

namespace Airline.Server.Domain.flight.Object;

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