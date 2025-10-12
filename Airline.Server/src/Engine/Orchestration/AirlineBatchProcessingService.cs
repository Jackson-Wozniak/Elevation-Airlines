using Airline.Server.Engine.Interface;
using Airline.Server.Engine.Service;

namespace Airline.Server.Engine.Orchestration;

public class AirlineBatchProcessingService(IServiceProvider serviceProvider,
    FlightPublisherService flightPublisherService) : BackgroundService
{
    private static readonly TimeOnly Midnight = TimeOnly.FromTimeSpan(TimeSpan.Zero);
    
    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        while(!stoppingToken.IsCancellationRequested)
        {
            var timeUntilMidnight = Midnight - TimeOnly.FromDateTime(DateTime.Now);
            await Task.Delay(timeUntilMidnight, stoppingToken);

            var today = DateOnly.FromDateTime(DateTime.Now);

            using var scope = serviceProvider.CreateScope();
            var flightSchedulerService = scope.ServiceProvider
                .GetRequiredService<IFlightSchedulerService>();
            var flightEventService = scope.ServiceProvider
                .GetRequiredService<FlightEventService>();
            var flights = flightSchedulerService.ScheduleAndSave(today);
            await flightPublisherService.PublishScheduledFlightAsync(flights);
            flightEventService.QueueEvents(today);
        }
    }
}