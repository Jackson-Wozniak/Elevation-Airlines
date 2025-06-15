package internal.parser.files;

import internal.parser.objects.AirportInfo;
import internal.parser.objects.CityAviationStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityAviationStatsCSVFile {
    private static CityAviationStatsCSVFile cityAviationStatsCSVFile;
    private static final String FILE_PATH = "city_aviation_stats.csv";

    private final List<CityAviationStats> cityStats = new ArrayList<>();

    private CityAviationStatsCSVFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader cityReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(cityReader).lines().toList());

        for (String[] allLine : allLines) {
            String year = allLine[0];
            String quarter = allLine[1];
            String city = allLine[2];
            String state = allLine[3];
            String connectedMarkets = allLine[4];
            String passengerCount = allLine[5];
            String averageFare = allLine[6];
            cityStats.add(new CityAviationStats(year, quarter, city, state, connectedMarkets, passengerCount, averageFare));
        }
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",", -1)).toList();
    }

    public static CityAviationStatsCSVFile getInstance() throws IOException {
        if(cityAviationStatsCSVFile == null){
            cityAviationStatsCSVFile = new CityAviationStatsCSVFile();
        }
        return cityAviationStatsCSVFile;
    }

    public List<CityAviationStats> getCityStats(){
        return cityStats;
    }
}
