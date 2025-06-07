package internal.parser.objects;

public class AirportOutput {
    private final String code;
    private final String size;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String continent;
    private final String country;
    private final int passengers;
    private final int runwayLengthFt;

    public AirportOutput(String code, String size, String name, String latitude, String longitude, String continent, String country, int passengers, int runwayLengthFt) {
        this.code = code;
        this.size = size;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.continent = continent;
        this.country = country;
        this.passengers = passengers;
        this.runwayLengthFt = runwayLengthFt;
    }

    public String getCode() {
        return code;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getRunwayLengthFt() {
        return runwayLengthFt;
    }
}
