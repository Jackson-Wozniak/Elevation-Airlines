using Airline.Server.Core.Infrastructure.EventQueue;
using Airline.Server.Domain.flight.Enum;
using Airline.Server.Domain.flight.Object;
using Airline.Server.Domain.flight.Service;
using Airline.Server.Engine.Service;

namespace Airline.Server.Engine.Orchestration;

public class FlightEventProcessor(
    IServiceProvider serviceProvider,
    PriorityEventQueue<FlightEvent> flightQueue,
    FlightPublisherService flightPublisherService,
    ILogger<FlightEventProcessor> logger) : BackgroundService
{
    protected override async Task ExecuteAsync(CancellationToken cancellationToken)
    {
        while (!cancellationToken.IsCancellationRequested)
        {
            var nextEvent = await flightQueue.DequeueAsync(cancellationToken);

            if (nextEvent == null) continue;

            var delay = nextEvent.ScheduledTime - DateTime.Now;
            
            logger.LogInformation($"Flight queue delaying until {nextEvent.ScheduledTime} waiting for next event");
            if (delay.Seconds > 0) await Task.Delay(delay, cancellationToken); 
            
            await HandleEvent(nextEvent);
        }
    }
    
    //TODO: this will not work once cache is implemented in flight service
    //cache will clear each new scope. A FlightCache will need to be made as singleton
    private async Task HandleEvent(FlightEvent flightEvent)
    {
        using var scope = serviceProvider.CreateScope();
        var newStatus = flightEvent.EventType.UpdatedFlightStatus();
        var flightService = scope.ServiceProvider.GetRequiredService<FlightService>();
        var flight = flightService.UpdateFlightStatus(flightEvent.FlightId, newStatus);
        await flightPublisherService.PublishFlightEventAsync(flightEvent, flight);
    }
}