package internal.parser.objects.csv;

import internal.parser.utils.CSVReaderUtils;

/*
Stores all information for a given airport, based on the specs from airports.csv in resources
 */
public class AirportCSVObject {
    private final String code;
    private final String size;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String continent;
    private final String country;
    private final String region;
    private final String regionCode;
    private final String city;
    private final String localCode;

    public AirportCSVObject(String code, String size, String name,
                            String latitude, String longitude,
                            String continent, String country,
                            String region, String regionCode, String city, String localCode) {
        this.code = code;
        this.size = mapSize(size);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.continent = continent;
        this.country = country;
        this.region = region;
        this.regionCode = regionCode;
        this.city = formatCity(city);
        this.localCode = localCode;
    }

    private static String mapSize(String size){
        return switch (size.toLowerCase()){
            case "small_airport" -> "SMALL";
            case "medium_airport" -> "MEDIUM";
            case "large_airport" -> "LARGE";
            default -> "UNKNOWN";
        };
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

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getLocalCode() {
        return localCode;
    }

    public String getRegionCode(){ return regionCode;}

    @Override
    public String toString() {
        return this.code + "," +
                this.size + "," +
                this.name + "," +
                this.latitude + "," +
                this.longitude + "," +
                CSVReaderUtils.continentCodeToName(this.continent) + "," +
                this.country + "," +
                this.region + "," +
                this.regionCode + "," +
                this.city + "\n";
    }

    private String formatCity(String str){
        if (str.contains("/")) {
            return str.split("/")[0];
        } else if(str.contains("(")){
            return str.split("\\(")[0];
        }
        return str;
    }

    public String getKey(){
        String tempCity;
        if (city.contains("/")) {
            String[] temp = city.split("/");
            tempCity = temp[0];
        } else if(city.contains("(")){
            String[] temp = city.split("\\(");
            tempCity = temp[0];
        }else{
            tempCity = city;
        }
        String key = tempCity + "," + regionCode;
        key = key.replace(" ", "").replace(".", "");
        return key;
    }
}
