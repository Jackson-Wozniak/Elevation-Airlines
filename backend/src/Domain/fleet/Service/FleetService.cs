using backend.Core.Data;
using backend.Core.Settings;
using backend.Domain.fleet.Entity;
using backend.Domain.fleet.Factory;
using backend.Domain.fleet.Repository;
using backend.Domain.flight.Entity;
using Microsoft.Extensions.Options;

namespace backend.Domain.fleet.Service;

public class FleetService(
    ApplicationDbContext context, 
    PlaneRepository planeRepository,
    FleetFactory fleetFactory,
    IOptions<SimulationSettings> options)
{
    private readonly SimulationSettings _simulationSettings = options.Value;
    
    public List<Plane> CreateFleet()
    {
        var callSigns = context.Planes.Select(p => p.CallSign).ToList();
        var fleet = fleetFactory.Create(_simulationSettings.FleetSize, callSigns);
        planeRepository.SavePlane(fleet);
        return fleet;
    }

    public Plane AddPlaneToFleet()
    {
        var callSigns = context.Planes.Select(p => p.CallSign).ToList();
        var plane = fleetFactory.Create(1, callSigns).First();
        planeRepository.SavePlane(plane);
        return plane;
    }

    public Dictionary<Plane, Flight?> LastFlightForPlanes()
    {
        var fleet = planeRepository.GetPlanes();
        Dictionary<Plane, Flight?> dictionary = [];
        foreach (var plane in fleet)
        {
            var lastFlight = plane.ScheduledFlights
                .OrderByDescending(f => f.BoardingTime)
                .FirstOrDefault();
            dictionary.Add(plane, lastFlight);
        }
        return dictionary;
    }
}