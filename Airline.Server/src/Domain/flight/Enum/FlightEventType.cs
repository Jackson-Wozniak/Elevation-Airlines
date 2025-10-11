namespace Airline.Server.Domain.flight.Enum;

public enum FlightEventType
{
    StartBoarding = 1,
    Takeoff = 2,
    Completed = 3,
    CompletedAndInspectPlane = 4
}

public static class FlightEventTypeUtils
{
    public static FlightStatus UpdatedFlightStatus(this FlightEventType type)
    {
        return type switch
        {
            FlightEventType.StartBoarding => FlightStatus.Boarding,
            FlightEventType.Takeoff => FlightStatus.InProgress,
            FlightEventType.Completed => FlightStatus.Completed,
            _ => FlightStatus.Scheduled
        };
    }
}