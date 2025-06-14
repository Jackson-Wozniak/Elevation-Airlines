package internal.parser.files;

import internal.parser.objects.AirportInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Stores all information for the airports.csv file, with an AirportInfo.java for each row
 */
public class AirportCSVFile {
    private static AirportCSVFile airportCSVFile;
    private static final String FILE_PATH = "data_utils/unparsed_data/airports.csv";

    private final List<AirportInfo> airports = new ArrayList<>();

    private AirportCSVFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader airportReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(airportReader).lines().toList());

        for (String[] allLine : allLines) {
            String code = allLine[0];
            String size = allLine[1];
            String name = allLine[2];
            String lat = allLine[3];
            String lon = allLine[4];
            String continent = allLine[5];
            String country = allLine[6];
            airports.add(new AirportInfo(code, size, name, lat, lon, continent, country));
        }
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).toList();
    }

    public static AirportCSVFile getInstance() throws IOException {
        if(airportCSVFile == null){
            airportCSVFile = new AirportCSVFile();
        }
        return airportCSVFile;
    }

    public List<AirportInfo> getAirports(){
        return airports;
    }
}
