using backend.Domain.flight.Entity;

namespace backend.Engine.Scheduling.Service;

public class FlightSchedulingService
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