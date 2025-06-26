package internal.parser.objects;

import internal.parser.utils.CSVReaderUtils;

public class AirportOutput {
    private final String code;
    private final String size;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String continent;
    private final String country;
    private final String region;
    private final String city;
    private final int runwayLengthFt;
    private final int passengerCount;

    public AirportOutput(AirportInfo airportInfo, int runwayLengthFt, int passengerCount) {
        this.code = airportInfo.getCode();
        this.size = mapSize(airportInfo.getSize());
        this.name = airportInfo.getName();
        this.latitude = airportInfo.getLatitude();
        this.longitude = airportInfo.getLongitude();
        this.continent = airportInfo.getContinent();
        this.country = airportInfo.getCountry();
        this.region = airportInfo.getRegionCode();
        this.city = airportInfo.getCity();
        this.runwayLengthFt = runwayLengthFt;
        this.passengerCount = passengerCount;
    }

    private static String mapSize(String size){
        return switch (size.toLowerCase()){
            case "small_airport" -> "SMALL";
            case "medium_airport" -> "MEDIUM";
            case "large_airport" -> "LARGE";
            default -> "UNKNOWN";
        };
    }

    @Override
    public String toString(){
        return this.code + "," +
                this.size + "," +
                this.name + "," +
                this.latitude + "," +
                this.longitude + "," +
                CSVReaderUtils.continentCodeToName(this.continent) + "," +
                this.country + "," +
                this.region + "," +
                this.city + "," +
                this.runwayLengthFt + "," +
                this.passengerCount + "\n";
    }
}
