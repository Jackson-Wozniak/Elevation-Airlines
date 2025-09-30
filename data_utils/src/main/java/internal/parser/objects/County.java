package internal.parser.objects;

import internal.parser.objects.csv.CountyCSVObject;
import internal.parser.utils.CSVReaderUtils;

import java.util.Arrays;

public class County {
    private final String county;
    private final String state;
    private final double[] gdpValues2001To2023;
    private final double CAGR;

    public County(CountyCSVObject c) {
        this.county = c.getCounty();
        this.state = c.getState();
        this.gdpValues2001To2023 = Arrays.stream(c.getGdpValues2001To2023())
                .mapToDouble(CSVReaderUtils::safeDoubleParse).toArray();
        this.CAGR = c.getCAGR();
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public double[] getGdpValues2001To2023() {
        return gdpValues2001To2023;
    }

    public double getCAGR(){
        return CAGR;
    }

    @Override
    public String toString(){
        double value = -1;
        try{
            value = (gdpValues2001To2023[gdpValues2001To2023.length - 1] * 1000);
        }catch (Exception ignored) { }
        return county + "," + state + "," + value + "," + CAGR + "\n";
    }

    public String getKey(){
        String key = this.county + "," + this.state;
        key = key.replace(" ", "");
        return key;
    }
}
