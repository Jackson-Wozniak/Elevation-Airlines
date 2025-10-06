using backend.Domain.flight.Entity;

namespace backend.Engine.Scheduling.Interface;

public interface IFlightSchedulerService
{
    List<Flight> ScheduleAndSave(DateOnly date);

    Dictionary<DateOnly, List<Flight>> ScheduleAndSave(DateOnly start, DateOnly end);
}