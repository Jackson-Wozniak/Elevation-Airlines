using backend.Core.Entity;
using backend.Domain.routenetwork.Enum;
using Route = backend.Domain.shared.Route;

namespace backend.Domain.routenetwork.Entity;

public class NetworkedRoute : BaseEntity
{
    public Route Route { get; set; }
    public int PlannedWeeklyFlights { get; set; }
    public RouteMarketType MarketType { get; set; }
}