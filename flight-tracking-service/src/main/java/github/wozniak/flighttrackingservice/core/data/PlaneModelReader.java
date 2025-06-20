package github.wozniak.flighttrackingservice.core.data;

import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import github.wozniak.flighttrackingservice.core.enums.AircraftCategory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class PlaneModelReader extends CSVReader<Aircraft> {
    private static final PlaneModelReader reader = new PlaneModelReader();
    private static final String INPUT_PATH = "text/planes.csv";

    protected static CSVReader<Aircraft> getInstance() {
        return reader;
    }

    @Override
    public List<Aircraft> read() throws IOException{
        ClassPathResource resource = new ClassPathResource(INPUT_PATH);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(this::mapPlanesFromLine).toList();
    }

    private Aircraft mapPlanesFromLine(String[] line){
        String make = line[0].split(" ")[0];
        String model = line[0].split(" ")[1];
        int cruiseSpeed = safeIntegerParse(line[1]);
        int range = safeIntegerParse(line[2]);
        AircraftCategory type = AircraftCategory.valueOf(line[3].toUpperCase());
        return new Aircraft.Builder(make, model, type).stats(cruiseSpeed, range).build();
    }
}
