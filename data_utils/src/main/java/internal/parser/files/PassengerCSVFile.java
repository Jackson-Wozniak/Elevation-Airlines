package internal.parser.files;

import internal.parser.utils.CSVReaderUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Stores all information for the passengers.csv file, with a PassengerInfo.java for each row
 */
public class PassengerCSVFile {
    private static PassengerCSVFile passengerCSVFile;
    private static final String FILE_PATH = "passengers.csv";

    private Map<String, Integer> passengersPerAirport = new HashMap<>();

    private PassengerCSVFile() throws IOException{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader passengerReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(passengerReader).lines().toList());

        for (String[] allLine : allLines) {
            if(allLine[0].equalsIgnoreCase("Airport")) continue;
            StringBuilder passengerStr = new StringBuilder(allLine[1]);
            if(allLine.length > 2){
                passengerStr = new StringBuilder();
                for(int i = 1; i < allLine.length; i++){
                    passengerStr.append(allLine[i]);
                }
            }
            String code = allLine[0];
            int passengers = CSVReaderUtils.safeIntegerParse(passengerStr.toString());

            passengersPerAirport.put(code, passengers);
        }
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).toList();
    }

    public static PassengerCSVFile getInstance() throws IOException {
        if(passengerCSVFile == null){
            passengerCSVFile = new PassengerCSVFile();
        }
        return passengerCSVFile;
    }

    public Map<String, Integer> getPassengersPerAirport(){
        return passengersPerAirport;
    }
}
