namespace backend.Core.Utils;

public class NumberUtils
{
    public static double GetDoubleOrDefault(string number, double defaultVal)
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
    
    public static int GetIntOrDefault(string number, int defaultVal)
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
}