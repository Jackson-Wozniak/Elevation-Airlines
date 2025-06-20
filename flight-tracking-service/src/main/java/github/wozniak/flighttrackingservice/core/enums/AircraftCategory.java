package github.wozniak.flighttrackingservice.core.enums;

public enum AircraftCategory {
    TURBOPROP("Turboprop"),
    PROPELLER("Propeller"),
    WIDE_BODY_AIRLINER("Wide Body Airliner"),
    NARROW_BODY_AIRLINER("Narrow Body Airliner"),
    REGIONAL_AIRLINER("Regional Airliner"),
    UNKNOWN("Unknown");

    private final String name;

    AircraftCategory(String name){
        this.name = name;
    }

    public static AircraftCategory fromString(String str){
        return switch (str.toUpperCase().replace(" ", "_")){
            case "REGIONAL_AIRLINER" -> REGIONAL_AIRLINER;
            case "PROPELLER" -> PROPELLER;
            case "WIDE_BODY_AIRLINER" -> WIDE_BODY_AIRLINER;
            case "NARROW_BODY_AIRLINER" -> NARROW_BODY_AIRLINER;
            case "TURBOPROP" -> TURBOPROP;
            default -> UNKNOWN;
        };
    }

    @Override
    public String toString(){
        return name;
    }
}
