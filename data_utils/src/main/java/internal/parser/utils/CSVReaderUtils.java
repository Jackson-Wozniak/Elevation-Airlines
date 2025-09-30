package internal.parser.utils;

import java.util.List;
import java.util.Locale;

public class CSVReaderUtils {

    public static int safeIntegerParse(String value){
        value = value.replace(",", "").replace("\"", "").trim();
        try{
            return Integer.parseInt(value);
        }catch (Exception ex){
            //TODO: if there is a parse exception, we have to manually go through each digit and verify it
            return 0;
        }
    }

    public static double safeDoubleParse(String value){
        value = value.replace(",", "").replace("\"", "").trim();
        try{
            return Double.parseDouble(value);
        }catch (Exception ex){
            //TODO: if there is a parse exception, we have to manually go through each digit and verify it
            return 0;
        }
    }

    public static double getPercentile(double value, List<Double> values){
        return (double) values.stream().filter(p -> p <= value)
                .count() / (double) values.size() * 100.0;
    }

    public static double averageRemoveZeroes(List<Double> values){
        return (int) Math.ceil(values.stream().mapToDouble(Double::doubleValue).sum()
                / (double) values.stream().filter(v -> v != 0).count());
    }

    public static String continentCodeToName(String continentCode){
        return switch(continentCode.toUpperCase()){
            case "NA" -> "North America";
            case "SA" -> "South America";
            case "AF" -> "Africa";
            case "AS" -> "Asia";
            case "EU" -> "Europe";
            default -> "Oceania";
        };
    }
}
