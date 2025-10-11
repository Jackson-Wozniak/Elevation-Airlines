using Airline.Server.Domain.flight.Entity;

namespace Airline.Server.Engine.Interface;

public interface IFlightSchedulerService
{
    List<Flight> ScheduleAndSave(DateOnly date);

    List<Flight> ScheduleAndSave(DateOnly start, DateOnly end);
}