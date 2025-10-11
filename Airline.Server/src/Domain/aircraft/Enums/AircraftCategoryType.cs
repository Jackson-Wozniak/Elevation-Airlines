namespace Airline.Server.Domain.aircraft.Enums;

public enum AircraftCategoryType
{
    NarrowBodyAirliner = 1,
    WideBodyAirliner = 2
}

public static class AircraftCategoryTypeUtils
{
    public static AircraftCategoryType? FromName(string str)
    {
        return str.ToLower() switch
        {
            "narrowbodyairliner" => AircraftCategoryType.NarrowBodyAirliner,
            "widebodyairliner" => AircraftCategoryType.WideBodyAirliner,
            _ => null
        };
    }
}