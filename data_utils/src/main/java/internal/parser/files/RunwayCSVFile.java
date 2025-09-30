package internal.parser.files;

import internal.parser.objects.csv.RunwayCSVObject;
import internal.parser.utils.CSVReaderUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Stores a map of all runways from runways.csv in resources folder
 */
public class RunwayCSVFile {
    private static RunwayCSVFile runwayCSVFile;
    private static final String FILE_PATH = "runways.csv";
    private static final int MINIMUM_RUNWAY_LENGTH = 4000;

    private Map<String, Integer> runwaysPerAirport = new HashMap<>();

    private RunwayCSVFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(inputStream == null) throw new IOException("Cannot load class");

        InputStreamReader runwayReader = new InputStreamReader(inputStream);

        List<String[]> allLines = toArray(new BufferedReader(runwayReader).lines().toList());

        Map<String, ArrayList<RunwayCSVObject>> possibleRunways = new HashMap<>();
        for (String[] allLine : allLines) {
            if(allLine[0].equalsIgnoreCase("Airport")) continue;
            String code = allLine[0];
            String runwayLengthStr = allLine[1];
            int runwayLength = CSVReaderUtils.safeIntegerParse(runwayLengthStr);
            String surface = allLine[2];
            boolean hasLights = allLine[3].equals("1");
            boolean isClosed = allLine[4].equals("1");

            if(!possibleRunways.containsKey(code)){
                possibleRunways.put(code, new ArrayList<>());
            }
            possibleRunways.get(code).add(new RunwayCSVObject(runwayLength, surface, hasLights, isClosed));
        }
        addAllowedAirports(possibleRunways);
    }

    //ensure at least one runway fits requirements for airport to be allowed in network
    private void addAllowedAirports(Map<String, ArrayList<RunwayCSVObject>> possibleRunways){
        possibleRunways.forEach((code, runwayInfos) -> {
            int maxLength = runwayInfos.stream()
                    .filter(runway -> runway.hasLights() && !runway.isClosed())
                    .mapToInt(RunwayCSVObject::getLengthFt)
                    .max().orElse(0);

            if(maxLength >= MINIMUM_RUNWAY_LENGTH) runwaysPerAirport.put(code, maxLength);
        });
    }

    private static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).toList();
    }

    public static RunwayCSVFile getInstance() throws IOException {
        if(runwayCSVFile == null){
            runwayCSVFile = new RunwayCSVFile();
        }
        return runwayCSVFile;
    }

    public Map<String, Integer> getRunwaysPerAirport(){
        return runwaysPerAirport;
    }
}
