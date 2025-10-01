namespace backend.Domain.aircraft.Enums;

public enum ManufacturerType
{
    Boeing = 1,
    Airbus = 2
}

public static class ManufacturerTypeUtils
{
    public static ManufacturerType? FromName(string str)
    {
        return str.ToLower() switch
        {
            "boeing" => ManufacturerType.Boeing,
            "airbus" => ManufacturerType.Airbus,
            _ => null
        };
    }
    
    public static string ToName(this ManufacturerType type)
    {
        return type switch
        {
            ManufacturerType.Airbus => "Airbus",
            ManufacturerType.Boeing => "Boeing",
            _ => "Unknown"
        };
    }
}