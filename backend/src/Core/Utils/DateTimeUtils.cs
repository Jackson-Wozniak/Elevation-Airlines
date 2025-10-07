namespace backend.Core.Utils;

public static class DateTimeUtils
{
    public static DateTime RoundUpToNearestQuarter(this DateTime time)
    {
        var doCarryDay = time.Hour >= 23;
        return time.Minute switch
        {
            <= 15 => new DateTime(time.Year, time.Month, time.Day, time.Hour, 15, 0),
            <= 30 => new DateTime(time.Year, time.Month, time.Day, time.Hour, 30, 0),
            <= 45 => new DateTime(time.Year, time.Month, time.Day, time.Hour, 45, 0),
            _ => new DateTime(time.Year, time.Month, doCarryDay ? time.Day + 1 : time.Day, 
                doCarryDay ? 0 : time.Hour + 1, 0, 0),
        };
    }
}