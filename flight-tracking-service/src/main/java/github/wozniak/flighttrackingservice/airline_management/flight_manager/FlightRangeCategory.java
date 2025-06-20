package github.wozniak.flighttrackingservice.airline_management.flight_manager;

public enum FlightRangeCategory {
    REGIONAL("Regional"),
    SHORT_HAUL("Short Haul"),
    LONG_HAUL("Long Haul"),
    ULTRA_LONG_HAUL("Ultra Long Haul"),
    UNKNOWN("Unknown");

    private final String name;

    FlightRangeCategory(String name){
        this.name = name;
    }

    public static FlightRangeCategory fromString(String str){
        return switch (str.toUpperCase().replace(" ", "_")){
            case "REGIONAL" -> REGIONAL;
            case "SHORT_HAUL" -> SHORT_HAUL;
            case "LONG_HAUL" -> LONG_HAUL;
            case "ULTRA_LONG_HAUL" -> ULTRA_LONG_HAUL;
            default -> UNKNOWN;
        };
    }

    @Override
    public String toString(){
        return name;
    }
}
