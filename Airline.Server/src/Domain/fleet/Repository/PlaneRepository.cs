using Airline.Server.Core.Data;
using Airline.Server.Core.Exception.types;
using Airline.Server.Domain.fleet.Entity;
using Microsoft.EntityFrameworkCore;

namespace Airline.Server.Domain.fleet.Repository;

public class PlaneRepository(ApplicationDbContext context)
{
    public List<Plane> GetPlanes()
    {
        return context.Planes
            .Include(p => p.Aircraft)
            .Include(p => p.ScheduledFlights)
            .ThenInclude(f => f.FlightPlan)
            .ThenInclude(f => f.Route)
            .ToList();
    }
    
    public Plane GetPlane(string callSign)
    {
        var plane = context.Planes.Include(p => p.Aircraft)
            .SingleOrDefault(p => p.CallSign.Equals(callSign));

        if (plane == null) throw new NotFoundException($"No call sign: {callSign}", "PlaneService.GetPlane");
        return plane;
    }
    
    public void DeleteAllPlanes()
    {
        context.Planes.RemoveRange(context.Planes);
        context.SaveChanges();
    }

    public void SavePlane(List<Plane> planes)
    {
        context.Planes.AddRange(planes);
        context.SaveChanges();
    }
    
    public void SavePlane(Plane plane)
    {
        context.Planes.Add(plane);
        context.SaveChanges();
    }
}