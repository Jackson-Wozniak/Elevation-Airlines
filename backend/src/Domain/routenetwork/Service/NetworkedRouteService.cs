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

    public List<NetworkedRoute> GetNetworkedRoutesFrom(string airport)
    {
        return context.NetworkedRoutes
            .Include(n => n.Route)
            .ThenInclude(r => r.Departure)
            .Include(n => n.Route)
            .ThenInclude(r => r.Destination)
            .Where(r => r.Route.Departure.AirportCode.Equals(airport))
            .ToList();
    }
    
    public List<NetworkedRoute> GetNetworkedRoutesTo(string airport)
    {
        return context.NetworkedRoutes
            .Include(n => n.Route)
            .ThenInclude(r => r.Departure)
            .Include(n => n.Route)
            .ThenInclude(r => r.Destination)
            .Where(r => r.Route.Destination.AirportCode.Equals(airport))
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