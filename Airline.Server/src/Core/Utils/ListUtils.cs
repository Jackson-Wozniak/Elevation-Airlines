namespace Airline.Server.Core.Utils;

public static class ListUtils
{
    private static readonly Random Rand = new Random();
    
    public static T Random<T>(this List<T> values)
    {
        return values[Rand.Next(0, values.Count)];
    }
}