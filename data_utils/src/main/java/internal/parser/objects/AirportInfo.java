package internal.parser.objects;

/*
Stores all information for a given airport, based on the specs from airports.csv in resources
 */
public class AirportInfo {
    private final String code;
    private final String size;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String continent;
    private final String country;

    public AirportInfo(String code, String size, String name, String latitude, String longitude, String continent, String country) {
        this.code = code;
        this.size = size;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.continent = continent;
        this.country = country;
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
}
