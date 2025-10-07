namespace backend.Core.Utils;

public static class DateUtils
{
    public static int DaysUntil(this DateOnly start, DateOnly end)
    {
        return end.DayNumber - start.DayNumber;
    }
}