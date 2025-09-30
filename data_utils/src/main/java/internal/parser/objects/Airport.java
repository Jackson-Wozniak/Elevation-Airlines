package internal.parser.objects;

import internal.parser.objects.csv.AirportCSVObject;

public class Airport {
    private final String code;
    private final String size;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String continent;
    private final String country;
    private final String state;
    private final String city;
    private final int passengerCount;
    public double economyValuePercentile = 0;

    public Airport(AirportCSVObject airportCSVObject, int passengerCount) {
        this.code = airportCSVObject.getCode();
        this.size = airportCSVObject.getSize();
        this.name = airportCSVObject.getName();
        this.latitude = airportCSVObject.getLatitude();
        this.longitude = airportCSVObject.getLongitude();
        this.continent = airportCSVObject.getContinent();
        this.country = airportCSVObject.getCountry();
        this.state = airportCSVObject.getRegionCode();
        this.city = airportCSVObject.getCity();
        this.passengerCount = passengerCount;
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
        String key = tempCity + "," + state;
        key = key.replace(" ", "").replace(".", "");
        return key;
    }

    @Override
    public String toString(){
        return this.code + "," +
                this.size + "," +
                this.name + "," +
                this.latitude + "," +
                this.longitude + "," +
                this.country + "," +
                this.state + "," +
                this.city + "," +
                this.economyValuePercentile + "\n";
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

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public int getPassengerCount() {
        return passengerCount;
    }
}
