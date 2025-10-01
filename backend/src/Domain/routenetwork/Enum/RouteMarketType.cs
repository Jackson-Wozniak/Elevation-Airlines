namespace backend.Domain.routenetwork.Enum;

public enum RouteMarketType
{
    HubToMajorMarket = 1,
    HubToSecondaryMarket = 2,
    ReturnToHub = 3,
    ConnectMajorMarkets = 4,
    ConnectSecondaryMarkets = 5
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
}