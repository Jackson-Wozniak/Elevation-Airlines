package internal.parser.utils;

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
}
