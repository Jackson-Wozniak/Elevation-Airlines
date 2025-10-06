using backend.Domain.flight.Entity;
using backend.Engine.Scheduling.Interface;

namespace backend.Engine.Scheduling.Service;

public class SimpleFlightSchedulerService : IFlightSchedulerService
{
    public List<Flight> Schedule(DateOnly date)
    {
        return [];
    }
    
    public Dictionary<DateOnly, List<Flight>> Schedule(DateOnly start, DateOnly end)
    {
        return [];
    }
}