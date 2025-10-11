namespace Airline.Server.Core.Utils;

public class RandomUtils
{
    private static readonly Random Rand = new Random();

    public static int Next(int lowerBound, int upperBound)
    {
        return Rand.Next(lowerBound, upperBound);
    }
}