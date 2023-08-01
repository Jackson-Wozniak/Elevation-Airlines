package github.wozniak.bankingservice.properties;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
public class SchedulingProperties {

    public static final String runAtMidnight = "0 0 0 * * *";

    public static final String runEveryHour = "0 0 * * * *";

    public static final long ONE_MINUTE = 60_000L;
}
