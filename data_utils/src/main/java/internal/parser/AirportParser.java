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
    private static final String AIRPORTS_OUTPUT = "./data_utils/output/airports.csv";
    private static final String COUNTY_ECONOMICS_OUTPUT = "./data_utils/output/county_economics.csv";

    public static void main( String[] args ) throws IOException {
        Map<String, Integer> passengers = PassengerCSVFile.getInstance().getPassengersPerAirport();
        Map<String, Integer> runways = RunwayCSVFile.getInstance().getRunwaysPerAirport();
        List<AirportInfo> airports = AirportCSVFile.getInstance().getAirports();
        Map<String, CityInfo> cityInfo = CitiesCountiesCSVFile.getInstance().getCities();
        Map<String, Map<String, CityAviationStats>> cityAviationStats = CityAviationStatsCSVFile.getInstance().getCityStats();

        List<AirportOutput> validAirports = new ArrayList<>();
        airports.forEach(airport -> {
            if(!airport.getCountry().equalsIgnoreCase("United States")) return;
            if(!runways.containsKey(airport.getCode())) return;

            int passengerCount = 0;
            if(passengers.containsKey(airport.getCode())){
                passengerCount = passengers.get(airport.getCode());
            }else if(passengers.containsKey(airport.getLocalCode())){
                passengerCount = passengers.get(airport.getLocalCode());
            }

            validAirports.add(new AirportOutput(airport, runways.get(airport.getCode()), passengerCount));
        });

        writeAirportsToOutput(validAirports);

        writeCountiesToOutput();
    }

    private static void writeAirportsToOutput(List<AirportOutput> airports){
        try (FileWriter writer = new FileWriter(AIRPORTS_OUTPUT)) {
            writer.write("Code,Size,Name,Latitude,Longitude,Continent,Country,Region,City,Runway Length (FT),Passengers-Per-Year\n");
            for(AirportOutput airport : airports){
                writer.write(airport.toString());
            }
            System.out.println("Airports written successfully to " + AIRPORTS_OUTPUT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void writeCountiesToOutput() throws IOException {
        Map<String, CountyEconomicInfo> countyEconomicInfo = CountyEconomicsCSVFile.getInstance().getCounties();
        try (FileWriter writer = new FileWriter(COUNTY_ECONOMICS_OUTPUT)) {
            writer.write("County, State, GDP ($), CAGR %\n");
            for(CountyEconomicInfo county : countyEconomicInfo.values()){
                writer.write(county.toString());
            }
            System.out.println("County Economics written successfully to " + COUNTY_ECONOMICS_OUTPUT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
