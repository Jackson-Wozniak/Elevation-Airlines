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
        return new Airport.Builder(line[0].trim(), line[2].trim())
                .coordinates(Double.parseDouble(line[3]), Double.parseDouble(line[4]))
                .location(line[5].trim(), line[6].trim())
                .specs(safeIntegerParse(line[7]), safeIntegerParse(line[8]))
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
