package internal.parser.files;

import internal.parser.objects.csv.CountyCSVObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CountyEconomicsCSVFile {
    private static CountyEconomicsCSVFile countyEconomicsCSVFile;
    private static final String FILE_PATH = "county_economics.csv";

    private final Map<String, CountyCSVObject> counties = new HashMap<>();

    private CountyEconomicsCSVFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader countyReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(countyReader).lines().toList());

        for (String[] allLine : allLines) {
            if(allLine[0].equalsIgnoreCase("Area")) continue;
            String county = allLine[0].replace("\"", "").trim();
            String state = allLine[1].replace("\"", "")
                    .replace("*", "").trim();
            String unit = allLine[2];
            String[] values = Arrays.copyOfRange(allLine, 3, allLine.length);
            CountyCSVObject info = new CountyCSVObject(county, state, unit, values);
            if(!counties.containsKey(info.getKey())) counties.put(info.getKey(), info);
        }
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",", -1)).toList();
    }

    public static CountyEconomicsCSVFile getInstance() throws IOException {
        if(countyEconomicsCSVFile == null){
            countyEconomicsCSVFile = new CountyEconomicsCSVFile();
        }
        return countyEconomicsCSVFile;
    }

    public Map<String, CountyCSVObject> getCounties(){
        return counties;
    }
}
