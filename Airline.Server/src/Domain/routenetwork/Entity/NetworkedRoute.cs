using Airline.Server.Core.Entity;
using Airline.Server.Domain.airport.Entity;
using Airline.Server.Domain.routenetwork.Enum;
using Route = Airline.Server.Domain.shared.Route;

namespace Airline.Server.Domain.routenetwork.Entity;

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