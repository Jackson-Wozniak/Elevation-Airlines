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

    public AirportOutput(AirportInfo airportInfo, int passengers, int runwayLengthFt) {
        this.code = airportInfo.getCode();
        this.size = airportInfo.getSize();
        this.name = airportInfo.getName();
        this.latitude = airportInfo.getLatitude();
        this.longitude = airportInfo.getLongitude();
        this.continent = airportInfo.getContinent();
        this.country = airportInfo.getCountry();
        this.passengers = passengers;
        this.runwayLengthFt = runwayLengthFt;
    }

    @Override
    public String toString(){
        return this.code + "," +
                this.size + "," +
                this.name + "," +
                this.latitude + "," +
                this.longitude + "," +
                this.continent + "," +
                this.country + "," +
                this.passengers + "," +
                this.runwayLengthFt + "\n";
    }
}
