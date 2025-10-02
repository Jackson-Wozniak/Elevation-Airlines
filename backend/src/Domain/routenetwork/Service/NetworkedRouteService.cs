using backend.Core.Data;
using backend.Domain.routenetwork.Entity;
using Microsoft.EntityFrameworkCore;

namespace backend.Domain.routenetwork.Service;

public class NetworkedRouteService(ApplicationDbContext context)
{
    public List<NetworkedRoute> GetNetworkedRoutes()
    {
        return context.NetworkedRoutes
            .Include(n => n.Route.Departure)
            .Include(n => n.Route.Destination)
            .ToList();
    }
    
    public void DeleteAllNetworkedRoutes()
    {
        context.NetworkedRoutes.RemoveRange(context.NetworkedRoutes);
        context.SaveChanges();
    }

    public void SaveNetworkedRoute(List<NetworkedRoute> routes)
    {
        context.NetworkedRoutes.AddRange(routes);
        context.SaveChanges();
    }
    
    public void SaveNetworkedRoute(NetworkedRoute route)
    {
        context.NetworkedRoutes.Add(route);
        context.SaveChanges();
    }
}