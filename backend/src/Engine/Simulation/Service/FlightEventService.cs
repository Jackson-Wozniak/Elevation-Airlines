using backend.Core.Infrastructure.EventQueue;
using backend.Domain.flight.Object;
using backend.Domain.flight.Service;

namespace backend.Engine.Simulation.Service;

public class FlightEventService(
    PriorityEventQueue<FlightEvent> eventQueue,
    FlightService flightService)
{
    public void QueueEvents(DateOnly date)
    {
        var flights = flightService.GetFlightsByDate(date);
        eventQueue.Enqueue(
            flights.SelectMany(f => f.CreateEvents()).ToList());
    }
}