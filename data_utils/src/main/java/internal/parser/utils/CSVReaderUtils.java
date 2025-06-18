package internal.parser.utils;

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
