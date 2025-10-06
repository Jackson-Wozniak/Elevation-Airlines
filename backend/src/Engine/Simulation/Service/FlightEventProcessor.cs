using backend.Core.Infrastructure.EventQueue;
using backend.Domain.flight.Object;
using backend.Domain.flight.Service;

namespace backend.Engine.Simulation.Service;

public class FlightEventProcessor(FlightService flightService) : BackgroundService
{
    private readonly PriorityEventQueue<FlightEvent> _queue = new();
    private readonly SemaphoreSlim _semaphore = new(0);
    
    public void QueueEvent(FlightEvent e)
    {
        _queue.Enqueue(e);
        _semaphore.Release();
    }

    public void QueueEvent(List<FlightEvent> events)
    {
        _queue.Enqueue(events);
        _semaphore.Release(events.Count);
    }

    protected override async Task ExecuteAsync(CancellationToken cancellationToken)
    {
        while (!cancellationToken.IsCancellationRequested)
        {
            await _semaphore.WaitAsync(cancellationToken);

            var nextEvent = _queue.Dequeue();

            if (nextEvent == null) continue;
            
            HandleEvent(nextEvent);
        }
    }
    
    private void HandleEvent(FlightEvent flightEvent){
        
    }
}