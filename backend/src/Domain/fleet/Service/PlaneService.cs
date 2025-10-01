using backend.Core.Data;
using backend.Domain.fleet.Entity;

namespace backend.Domain.fleet.Service;

public class PlaneService(ApplicationDbContext context)
{
    public void DeleteAllPlanes()
    {
        context.RemoveRange(context.Planes);
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