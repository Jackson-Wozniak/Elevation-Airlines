using backend.Core.Data;
using backend.Core.Settings;
using backend.Domain.fleet.Entity;
using backend.Domain.fleet.Factory;

namespace backend.Domain.fleet.Service;

public class FleetService(
    ApplicationDbContext context, 
    PlaneService planeService,
    FleetFactory fleetFactory,
    SimulationSettings simulationSettings)
{
    public List<Plane> CreateFleet()
    {
        var callSigns = context.Planes.Select(p => p.CallSign).ToList();
        var fleet = fleetFactory.Create(simulationSettings.FleetSize, callSigns);
        planeService.SavePlane(fleet);
        return fleet;
    }

    public Plane AddPlaneToFleet()
    {
        var callSigns = context.Planes.Select(p => p.CallSign).ToList();
        var plane = fleetFactory.Create(1, callSigns).First();
        planeService.SavePlane(plane);
        return plane;
    }
}