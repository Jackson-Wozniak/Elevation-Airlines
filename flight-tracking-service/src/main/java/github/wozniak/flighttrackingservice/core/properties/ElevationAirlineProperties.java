package github.wozniak.flighttrackingservice.core.properties;

public class ElevationAirlineProperties {
    public static final boolean DELETE_DATA_ON_STARTUP_MODE = false;
    public static final int FLEET_COUNT = 10;

    public static final double DEFAULT_BUDGET = 1_000_000_000.00;
    public static int[] CALLSIGN_RANGE = {100, 1000};
    public static String CALLSIGN_PREFIX = "ELEV";
    public static String PRIMARY_HUB_CODE = "KBOS";
}
