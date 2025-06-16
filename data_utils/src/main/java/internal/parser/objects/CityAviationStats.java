package internal.parser.objects;

public class CityAviationStats {
    private final String year;
    private final String quarter;
    private final String city;
    private final String state;
    private final String connectedMarkets;
    private final String passengerCount;
    private final String averageFareUSD;

    public CityAviationStats(String year, String quarter, String city, String state,
                             String connectedMarkets, String passengerCount, String averageFareUSD) {
        this.averageFareUSD = averageFareUSD;
        this.passengerCount = passengerCount;
        this.connectedMarkets = connectedMarkets;
        this.state = state;
        this.city = city;
        this.quarter = quarter;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getConnectedMarkets() {
        return connectedMarkets;
    }

    public String getPassengerCount() {
        return passengerCount;
    }

    public String getAverageFareUSD() {
        return averageFareUSD;
    }

    public String getKey(){
        String key = this.city + "," + this.state;
        key = key.replace(" ", "");
        return key;
    }

    public String getTimePeriod(){
        return "Q" + this.quarter + " " + this.year;
    }
}
