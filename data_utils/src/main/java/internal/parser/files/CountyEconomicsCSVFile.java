package internal.parser.files;

import internal.parser.objects.CountyEconomicInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountyEconomicsCSVFile {
    private static CountyEconomicsCSVFile countyEconomicsCSVFile;
    private static final String FILE_PATH = "county_economics.csv";

    private final List<CountyEconomicInfo> counties = new ArrayList<>();

    private CountyEconomicsCSVFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader countyReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(countyReader).lines().toList());

        for (String[] allLine : allLines) {
            String county = allLine[0].replace("\"", "").trim();
            String state = allLine[1].replace("\"", "")
                    .replace("*", "").trim();
            String unit = allLine[2];
            String[] values = Arrays.copyOfRange(allLine, 3, allLine.length);
            counties.add(new CountyEconomicInfo(county, state, unit, values));
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

    public List<CountyEconomicInfo> getCounties(){
        return counties;
    }
}
