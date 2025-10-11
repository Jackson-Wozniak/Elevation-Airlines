namespace Airline.Server.Core.Infrastructure.EventQueue;

public abstract class Event
{
    public DateTime ScheduledTime { get; set; }
}