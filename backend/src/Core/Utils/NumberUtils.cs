namespace backend.Core.Utils;

public class NumberUtils
{
    public static double getDoubleOrDefault(string number, double defaultVal)
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
}