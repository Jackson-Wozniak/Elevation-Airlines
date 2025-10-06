namespace backend.Core.Infrastructure.EventQueue;

public abstract class Event
{
    public DateTime ScheduledTime { get; set; }
}