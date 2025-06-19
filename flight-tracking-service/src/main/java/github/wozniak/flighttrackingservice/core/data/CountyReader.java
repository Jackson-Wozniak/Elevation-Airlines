package github.wozniak.flighttrackingservice.core.data;

import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.CityStats;
import github.wozniak.flighttrackingservice.economics.entity.County;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class CountyReader extends CSVReader<County> {
    private static final CountyReader reader = new CountyReader();
    private static final String INPUT_PATH = "text/county_economics.csv";

    protected static CSVReader<County> getInstance() {
        return reader;
    }

    @Override
    public List<County> read() throws IOException {
        ClassPathResource resource = new ClassPathResource(INPUT_PATH);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(this::mapCountyFromLine).toList();
    }

    private County mapCountyFromLine(String[] line){
        String name = line[0];
        String state = line[1];
        long gdp = safeLongParse(line[2]);
        double cagr = safeDoubleParse(line[3]);
        return new County(name, state, gdp, cagr);
    }
}
