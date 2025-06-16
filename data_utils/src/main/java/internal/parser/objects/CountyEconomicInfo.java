package internal.parser.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountyEconomicInfo {
    private final String county;
    private final String state;
    private final String unit;
    private final String[] gdpValues2001To2023;
    private double CAGR;

    public CountyEconomicInfo(String county, String state, String unit, String[] gdpValues2001To2023) {
        this.county = county;
        this.state = state;
        this.unit = unit;
        this.gdpValues2001To2023 = gdpValues2001To2023;
        setCAGR();
    }

    private void setCAGR(){
        List<Double> values = new ArrayList<>();
        for(String gdp : gdpValues2001To2023){
            try{
                double value = Double.parseDouble(gdp);
                values.add(value);
            }catch (Exception ignored) { }
        }

        double rate = (Math.pow(values.get(values.size() - 1) / values.get(0), (1.0 / values.size()))) - 1;

        this.CAGR = Math.round((rate * 100.0) * 100.00) / 100.00;
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

    public double getCAGR(){
        return CAGR;
    }

    @Override
    public String toString(){
        long value = -1;
        try{
            value = (Long.parseLong(gdpValues2001To2023[gdpValues2001To2023.length - 1]) * 1000);
        }catch (Exception ignored) { }
        return county + "," + state + "," + value + "," + CAGR + "\n";
    }

    public String getKey(){
        String key = this.county + "," + this.state;
        key = key.replace(" ", "");
        return key;
    }
}
