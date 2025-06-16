package internal.parser.objects;

import java.util.Arrays;

public class CountyEconomicInfo {
    private String county;
    private String state;
    private String unit;
    private String[] gdpValues2001To2023;

    public CountyEconomicInfo(String county, String state, String unit, String[] gdpValues2001To2023) {
        this.county = county;
        this.state = state;
        this.unit = unit;
        this.gdpValues2001To2023 = gdpValues2001To2023;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getUnit() {
        return unit;
    }

    public String[] getGdpValues2001To2023() {
        return gdpValues2001To2023;
    }

    @Override
    public String toString(){
        return county + "," + state + "," + unit + "," + Arrays.toString(gdpValues2001To2023);
    }

    public String getKey(){
        String key = this.county + "," + this.state;
        key = key.replace(" ", "");
        return key;
    }
}
