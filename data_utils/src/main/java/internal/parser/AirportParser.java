package internal.parser;

import internal.parser.files.*;
import internal.parser.objects.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AirportParser {
    private static final String OUTPUT_PATH = "./data_utils/output/airports.csv";

    public static void main( String[] args ) throws IOException {
        Map<String, Integer> passengers = PassengerCSVFile.getInstance().getPassengersPerAirport();
        Map<String, Integer> runways = RunwayCSVFile.getInstance().getRunwaysPerAirport();
        List<AirportInfo> airports = AirportCSVFile.getInstance().getAirports();
        Map<String, CountyEconomicInfo> countyEconomicInfo = CountyEconomicsCSVFile.getInstance().getCounties();
        Map<String, CityInfo> cityInfo = CitiesCountiesCSVFile.getInstance().getCities();
        Map<String, Map<String, CityAviationStats>> cityAviationStats = CityAviationStatsCSVFile.getInstance().getCityStats();


//        List<AirportOutput> validAirports = new ArrayList<>();
//
//        airports.forEach(airport -> {
//            if(!runways.containsKey(airport.getCode())) return;
//
//            int passengerCount = 0;
//            if(passengers.containsKey(airport.getCode())){
//                passengerCount = passengers.get(airport.getCode());
//            }else if(passengers.containsKey(airport.getLocalCode())){
//                passengerCount = passengers.get(airport.getLocalCode());
//            }
//
//            validAirports.add(new AirportOutput(airport, runways.get(airport.getCode()), passengerCount));
//        });
//
//        writeAirportsToOutput(validAirports);
    }

    private static void writeAirportsToOutput(List<AirportOutput> airports){
        try (FileWriter writer = new FileWriter(OUTPUT_PATH)) {
            writer.write("Code,Size,Name,Latitude,Longitude,Continent,Country,Region,City,Runway Length (FT),Passengers-Per-Year\n");
            for(AirportOutput airport : airports){
                writer.write(airport.toString());
            }
            System.out.println("Airports written successfully to " + OUTPUT_PATH);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
