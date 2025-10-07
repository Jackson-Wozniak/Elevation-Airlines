using backend.Core.Settings;
using backend.Domain.fleet.Service;
using backend.Domain.flight.Entity;
using backend.Domain.routenetwork.Service;
using backend.Engine.Interface;
using Microsoft.Extensions.Options;

namespace backend.Engine.Service;

public class SimpleFlightSchedulerService(
    NetworkedRouteService networkedRouteService,
    PlaneService planeService,
    IOptions<SimulationSettings> options,
    ILogger<SimpleFlightSchedulerService> logger) : IFlightSchedulerService
{
    private readonly SimulationSettings _settings = options.Value;
    
    public List<Flight> ScheduleAndSave(DateOnly date)
    {
        return [];
    }
    
    public Dictionary<DateOnly, List<Flight>> ScheduleAndSave(DateOnly start, DateOnly end)
    {
        return [];
    }
}