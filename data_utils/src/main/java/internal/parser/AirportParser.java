package internal.parser;

import internal.parser.files.*;
import internal.parser.objects.*;
import internal.parser.objects.csv.AirportCSVObject;
import internal.parser.objects.csv.CityCSVObject;
import internal.parser.objects.csv.CityStatsCSVObject;
import internal.parser.objects.csv.CountyCSVObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AirportParser {
    private static final String AIRPORTS_OUTPUT = "./data_utils/output/airports.csv";

    /*
    Steps:
        1. Read all cities, counties and airports
        2. Filter out airports that are not in the United States
        3. Filter out airports that are not large or medium size
        4. Filter out airports without a known city, and without any passenger data
        5. Calculate the economic value percentile by averaging the non-zero percentiles
            for key economic stats (airport passengers, city population, county gdp etc.)
        6. Write that data to outputs/airports.csv to be copied into the server resources folder
     */
    public static void main( String[] args ) throws IOException {
        List<Airport> airports = readValidAirports();
        Map<String, County> counties = readCounties();
        Map<String, City> cities = readCities();

        EconomySizeService economySizeService = new EconomySizeService(airports,
                cities.values().stream().toList(), counties.values().stream().toList());
        airports.forEach(a -> {
            if(!cities.containsKey(a.getKey())) return;

            City city = cities.get(a.getKey());
            double economySize;
            if(counties.containsKey(city.getCountyKey())){
                economySize = economySizeService.calculateEconomySize(a, city);
            }else{
                County county = counties.get(city.getCountyKey());
                economySize = economySizeService.calculateEconomySize(a, city, county);
            }
            a.economyValuePercentile = economySize;
        });
        writeAirportsToOutput(airports);
    }

    public static List<Airport> readValidAirports() throws IOException {
        Map<String, Integer> passengers = PassengerCSVFile.getInstance().getPassengersPerAirport();
        List<AirportCSVObject> airports = AirportCSVFile.getInstance().getAirports();

        List<Airport> validAirports = new ArrayList<>();

        /*
        - Filter first to ignore any non-US based airports.
        - Filter small or unknown airports so it is only medium and large airports
         */
        airports.forEach(airport -> {
            if(!airport.getCountry().equalsIgnoreCase("UNITED STATES")) return;
            if(airport.getSize().equalsIgnoreCase("SMALL")) return;
            if(airport.getSize().equalsIgnoreCase("UNKNOWN")) return;

            int passengerCount = 0;
            if(passengers.containsKey(airport.getCode())){
                passengerCount = passengers.get(airport.getCode());
            }else if(passengers.containsKey(airport.getLocalCode())){
                passengerCount = passengers.get(airport.getLocalCode());
            }else{
                passengerCount = -1;
            }

            validAirports.add(new Airport(airport, passengerCount));
        });
        return validAirports;
    }

    public static Map<String, County> readCounties() throws IOException {
        Map<String, CountyCSVObject> countyEconomicInfo = CountyEconomicsCSVFile.getInstance().getCounties();
        return countyEconomicInfo.values().stream()
                .collect(Collectors.toMap(CountyCSVObject::getKey, County::new));
    }

    public static Map<String, City> readCities() throws IOException {
        Map<String, CityCSVObject> cityInfo = CitiesCountiesCSVFile.getInstance().getCities();
        Map<String, Map<String, CityStatsCSVObject>> cityAviationStats = CityAviationStatsCSVFile.getInstance().getCityStats();

        Map<String, City> cityStats = new HashMap<>();
        for(CityCSVObject city : cityInfo.values()){
            Map<String, CityStatsCSVObject> passengersByQuarter;
            if(!cityAviationStats.containsKey(city.getKey())){
                passengersByQuarter = new HashMap<>();
            }else{
                passengersByQuarter = cityAviationStats.get(city.getKey());
            }
            City output = City.create(city, passengersByQuarter);
            cityStats.put(city.getKey(), output);
        }
        return cityStats;
    }

    private static void writeAirportsToOutput(List<Airport> airports){
        try (FileWriter writer = new FileWriter(AIRPORTS_OUTPUT)) {
            writer.write("#Code,Size,Name,Latitude,Longitude,Country,State,City,Economic Value Percentile\n");
            for(Airport airport : airports){
                writer.write(airport.toString());
            }
            System.out.println("Airports written successfully to " + AIRPORTS_OUTPUT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
