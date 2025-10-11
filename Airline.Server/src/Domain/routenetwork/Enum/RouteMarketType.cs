using Airline.Server.Domain.airport.Entity;
using Airline.Server.Domain.airport.Enum;

namespace Airline.Server.Domain.routenetwork.Enum;

public enum RouteMarketType
{
    HubToMajorMarket = 1,
    HubToSecondaryMarket = 2,
    ReturnToHub = 3,
    ConnectMajorMarkets = 4,
    ConnectSecondaryMarkets = 5,
    HubToNiche = 6
}

public static class RouteMarketTypeUtils
{
    public static bool ArrivesAtHub(this RouteMarketType type)
    {
        return type == RouteMarketType.ReturnToHub;
    }

    public static bool DepartsFromHub(this RouteMarketType type)
    {
        return type is RouteMarketType.HubToMajorMarket
            or RouteMarketType.HubToSecondaryMarket;
    }

    public static RouteMarketType MapFromAirports(Airport dep, Airport dest,
        bool includesHub, bool departsHub)
    {
        if (!includesHub)
        {
            if (dep.MarketType == MarketType.Major && dest.MarketType == MarketType.Major)
                return RouteMarketType.ConnectMajorMarkets;
            return RouteMarketType.ConnectSecondaryMarkets;
        }

        if (!departsHub) return RouteMarketType.ReturnToHub;
        
        return dest.MarketType switch
        {
            MarketType.Major => RouteMarketType.HubToMajorMarket,
            MarketType.Secondary => RouteMarketType.HubToSecondaryMarket,
            _ => RouteMarketType.HubToNiche
        };
    }
}