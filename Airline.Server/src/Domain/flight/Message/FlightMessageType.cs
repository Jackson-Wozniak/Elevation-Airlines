using Airline.Server.Domain.flight.Enum;
using Airline.Server.Domain.flight.Object;

namespace Airline.Server.Domain.flight.Message;

public enum FlightMessageType
{
    Scheduled,
    Boarding,
    Takeoff,
    Completed
}

public static class FlightMessageTypeUtils
{
    public static FlightMessageType? MessageType(this FlightEvent e)
    {
        return e.EventType switch
        {
            FlightEventType.StartBoarding => FlightMessageType.Boarding,
            FlightEventType.Takeoff => FlightMessageType.Takeoff,
            FlightEventType.Completed => FlightMessageType.Completed,
            _ => null
        };
    }
}