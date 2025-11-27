using Airline.Server.Core.Data;
using Airline.Server.Engine.Settings;
using Airline.Server.Domain.fleet.Entity;
using Airline.Server.Domain.fleet.Factory;
using Airline.Server.Domain.fleet.Repository;
using Airline.Server.Domain.flight.Entity;
using Microsoft.Extensions.Options;

namespace Airline.Server.Domain.fleet.Service;

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