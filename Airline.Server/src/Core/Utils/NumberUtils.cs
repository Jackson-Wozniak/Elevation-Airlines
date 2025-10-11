namespace Airline.Server.Core.Utils;

public static class NumberUtils
{
    public static double GetDoubleOrDefault(this string number, double defaultVal)
    {
        try
        {
            return Double.Parse(number);
        }
        catch
        {
            return defaultVal;
        }
    }
    
    public static int GetIntOrDefault(this string number, int defaultVal)
    {
        try
        {
            return Int32.Parse(number);
        }
        catch
        {
            return defaultVal;
        }
    }

    public static double ToRadians(this double degrees)
    {
        return degrees * (Math.PI / 180.0);
    }
}