using backend.Engine.Scheduling.Interface;
using backend.Engine.Simulation.Service;

namespace backend.Engine.Scheduling.Service;

public class AirlineBatchProcessingService(IServiceProvider serviceProvider) : BackgroundService
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
            flightSchedulerService.ScheduleAndSave(today);
            flightEventService.QueueEvents(today);
        }
    }
}