namespace Airline.Server.Domain.airport.Enum;

public enum MarketType
{
    Major = 1,
    Secondary = 2,
    Local = 3,
    Rural = 4
}

public static class MarketTypeUtils
{
    public static MarketType FromEconomicPercentile(double percentile)
    {
        return percentile switch
        {
            > 80.0 => MarketType.Major,
            > 50.0 => MarketType.Secondary,
            > 25.0 => MarketType.Local,
            _ => MarketType.Rural
        };
    }
}