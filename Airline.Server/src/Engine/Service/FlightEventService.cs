using Airline.Server.Core.Infrastructure.EventQueue;
using Airline.Server.Domain.flight.Entity;
using Airline.Server.Domain.flight.Object;
using Airline.Server.Domain.flight.Service;

namespace Airline.Server.Engine.Service;

public class FlightEventService(
    PriorityEventQueue<FlightEvent> eventQueue,
    FlightService flightService)
{
    public void QueueEvents(DateOnly date)
    {
        var flights = flightService.GetFlightsByDate(date);
        eventQueue.Enqueue(flights.SelectMany(f => f.CreateEvents()).ToList());
    }
    
    public void QueueEvents(List<Flight> flights)
    {
        eventQueue.Enqueue(flights.SelectMany(f => f.CreateEvents()).ToList());
    }
}