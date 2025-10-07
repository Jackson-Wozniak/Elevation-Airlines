using backend.Core.Infrastructure.EventQueue;
using backend.Domain.flight.Enum;
using backend.Domain.flight.Object;
using backend.Domain.flight.Service;

namespace backend.Engine.Orchestration;

public class FlightEventProcessor(
    IServiceProvider serviceProvider,
    PriorityEventQueue<FlightEvent> flightQueue) : BackgroundService
{
    protected override async Task ExecuteAsync(CancellationToken cancellationToken)
    {
        while (!cancellationToken.IsCancellationRequested)
        {
            var nextEvent = await flightQueue.DequeueAsync(cancellationToken);

            if (nextEvent == null) continue;
            
            HandleEvent(nextEvent);
        }
    }
    
    //TODO: this will not work once cache is implemented in flight service
    //cache will clear each new scope. A FlightCache will need to be made as singleton
    private void HandleEvent(FlightEvent flightEvent)
    {
        using var scope = serviceProvider.CreateScope();
        var newStatus = flightEvent.EventType.UpdatedFlightStatus();
        var flightService = scope.ServiceProvider.GetRequiredService<FlightService>();
        flightService.UpdateFlightStatus(flightEvent.FlightId, newStatus);
    }
}