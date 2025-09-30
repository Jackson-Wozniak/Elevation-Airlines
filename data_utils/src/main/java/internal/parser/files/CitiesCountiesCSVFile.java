package internal.parser.files;

import internal.parser.objects.csv.CityCSVObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitiesCountiesCSVFile {
    private static CitiesCountiesCSVFile citiesCountiesCSVFile;
    private static final String FILE_PATH = "cities_and_counties.csv";

    private final Map<String, CityCSVObject> cities = new HashMap<>();

    private CitiesCountiesCSVFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader cityReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(cityReader).lines().toList());

        for (String[] allLine : allLines) {
            if(allLine[0].equalsIgnoreCase("City")) continue;
            String name = allLine[0];
            String state = allLine[1];
            String county = allLine[3];
            String population = allLine[4];
            String ranking = allLine[5];
            CityCSVObject cityCSVObject = new CityCSVObject(name, state, county, population, ranking);
            if(!cities.containsKey(cityCSVObject.getKey())) cities.put(cityCSVObject.getKey(), cityCSVObject);
        }
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",", -1)).toList();
    }

    public static CitiesCountiesCSVFile getInstance() throws IOException {
        if(citiesCountiesCSVFile == null){
            citiesCountiesCSVFile = new CitiesCountiesCSVFile();
        }
        return citiesCountiesCSVFile;
    }

    public Map<String, CityCSVObject> getCities(){
        return cities;
    }
}
