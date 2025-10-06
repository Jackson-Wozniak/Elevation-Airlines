using backend.Domain.flight.Entity;

namespace backend.Engine.Scheduling.Interface;

public interface IFlightSchedulerService
{
    List<Flight> Schedule(DateOnly date);

    Dictionary<DateOnly, List<Flight>> Schedule(DateOnly start, DateOnly end);
}