package github.wozniak.flighttrackingservice.core.data;

import github.wozniak.flighttrackingservice.core.entity.PlaneModel;
import github.wozniak.flighttrackingservice.core.enums.ModelType;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class PlaneModelReader extends CSVReader<PlaneModel> {
    private static final PlaneModelReader reader = new PlaneModelReader();
    private static final String INPUT_PATH = "text/planes.csv";

    protected static CSVReader<PlaneModel> getInstance() {
        return reader;
    }

    @Override
    public List<PlaneModel> read() throws IOException{
        ClassPathResource resource = new ClassPathResource(INPUT_PATH);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(this::mapPlanesFromLine).toList();
    }

    private PlaneModel mapPlanesFromLine(String[] line){
        String make = line[0].split(" ")[0];
        String model = line[0].split(" ")[1];
        int cruiseSpeed = safeIntegerParse(line[1]);
        int range = safeIntegerParse(line[2]);
        ModelType type = ModelType.valueOf(line[3].toUpperCase());
        return new PlaneModel.Builder(make, model, type).stats(cruiseSpeed, range).build();
    }
}
