package github.wozniak.flighttrackingservice.core.data;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.CityStats;
import github.wozniak.flighttrackingservice.economics.entity.County;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class CityReader extends CSVReader<City> {
    private static final CityReader reader = new CityReader();
    private static final String INPUT_PATH = "text/city_info.csv";

    protected static CityReader getInstance() {
        return reader;
    }

    public List<City> read(Map<String, County> counties) throws IOException {
        ClassPathResource resource = new ClassPathResource(INPUT_PATH);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        return allLines.stream().map(line -> mapCityFromLine(line, counties)).toList();
    }

    private City mapCityFromLine(String[] line, Map<String, County> counties){
        String name = line[0];
        String state = line[1];
        County county = counties.getOrDefault((line[2] + ", " + state),
                counties.get("UNKNOWN, UNKNOWN"));
        long population = safeLongParse(line[3]);
        String ranking = line[4];
        int connectedMarkets = safeIntegerParse(line[5]);
        long q1 = safeLongParse(line[6]);
        long q2 = safeLongParse(line[7]);
        long q3 = safeLongParse(line[8]);
        long q4 = safeLongParse(line[9]);
        double cagr = safeDoubleParse(line[10]);

        return new City(name, state, county,
                new CityStats(population, ranking, connectedMarkets, q1, q2, q3, q4, cagr));
    }
}
