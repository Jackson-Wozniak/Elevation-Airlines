package github.wozniak.flighttrackingservice.core.data;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AirportReader extends CSVReader<Airport> {
    private static final AirportReader reader = new AirportReader();
    private static final String INPUT_PATH = "text/airports.csv";

    protected static CSVReader<Airport> getInstance() {
        return reader;
    }

    @Override
    public List<Airport> read() throws IOException{
        ClassPathResource resource = new ClassPathResource(INPUT_PATH);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(this::mapAirportFromLine).toList();
    }

    private Airport mapAirportFromLine(String[] line){
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
}
