using backend.Core.Settings;
using backend.Core.Utils;
using backend.Domain.fleet.Entity;
using backend.Domain.fleet.Service;
using backend.Domain.flight.Entity;
using backend.Domain.flight.Service;
using backend.Domain.routenetwork.Service;
using backend.Engine.Interface;
using Microsoft.Extensions.Options;

namespace backend.Engine.Service;

public class SimpleFlightSchedulerService(
    NetworkedRouteService networkedRouteService,
    PlaneService planeService,
    FlightService flightService,
    IOptions<SimulationSettings> options,
    ILogger<SimpleFlightSchedulerService> logger) : IFlightSchedulerService
{
    private readonly SimulationSettings _settings = options.Value;
    
    public List<Flight> ScheduleAndSave(DateOnly date)
    {
        var time = date.ToDateTime(new TimeOnly(1, 0));
        var planes = planeService.GetPlanes();
        var networkedRoutes = networkedRouteService.GetNetworkedRoutes();
        return planes.Select(p => 
            Flight.Create(networkedRoutes.Random().Route, p, time, 
                time.AddMinutes(_settings.LayoverMinutes)))
            .ToList();
    }
    
    public List<Flight> ScheduleAndSave(DateOnly start, DateOnly end)
    {
        List<Flight> flightSchedule = [];
        var daysBetween = start.DaysUntil(end);
        for (var i = 0; i < daysBetween; i++)
        {
            var flights = ScheduleAndSave(start.AddDays(i));
            flightSchedule.AddRange(flights);
        }
        return flightService.SaveFlights(flightSchedule);
    }
}