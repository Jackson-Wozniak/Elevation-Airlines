package github.wozniak.flighttrackingservice.core.utils;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.model.PlaneModel;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReader {

    private static final String airportsPath = "text/airports.csv";
    private static final String planesPath = "text/planes.csv";

    public static List<Airport> readAllAirports() throws IOException {
        ClassPathResource resource = new ClassPathResource(airportsPath);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(CSVReader::mapAirportFromLine).toList();
    }

    public static List<PlaneModel> readAllPlaneModels() throws IOException {
        ClassPathResource resource = new ClassPathResource(planesPath);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(CSVReader::mapPlanesFromLine).toList();
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).toList();
    }

    private static Airport mapAirportFromLine(String[] line){
        String code = line[0];
        String size = line[1];
        String name = line[2];
        double latitude = Double.parseDouble(line[3]);
        double longitude = Double.parseDouble(line[4]);
        String continent = line[5];
        String country = line[6];
        String state = line[7];
        String city = line[8];
        int runwayLengthFt = safeIntegerParse(line[9]);
        int passengersPerYear = safeIntegerParse(line[10]);

        return new Airport.Builder(code, name, size)
                .coordinates(latitude, longitude)
                .location(continent, country, state, city)
                .specs(passengersPerYear, runwayLengthFt)
                .build();
    }

    private static int safeIntegerParse(String value){
        try{
            return Integer.parseInt(value);
        }catch(Exception ex){
            //TODO: manually go through each digit if needed
            return 0;
        }
    }

    private static PlaneModel mapPlanesFromLine(String[] line){
        return new PlaneModel(line[0], 0, 0, Integer.parseInt(line[1]), Integer.parseInt(line[2]));
    }
}
