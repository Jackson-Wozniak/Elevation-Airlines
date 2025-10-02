using backend.Core.Entity;
using backend.Domain.airport.Entity;
using backend.Domain.routenetwork.Enum;
using Route = backend.Domain.shared.Route;

namespace backend.Domain.routenetwork.Entity;

public class NetworkedRoute : BaseEntity
{
    public Route Route { get; set; }
    public int PlannedWeeklyFlights { get; set; }
    public RouteMarketType MarketType { get; set; }
    
    protected NetworkedRoute() { }

    public NetworkedRoute(Airport departure, Airport destination,
        int weeklyFlight, RouteMarketType type)
    {
        Route = new Route(departure, destination);
    }
}