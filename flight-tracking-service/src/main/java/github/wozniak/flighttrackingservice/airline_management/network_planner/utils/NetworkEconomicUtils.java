package github.wozniak.flighttrackingservice.airline_management.network_planner.utils;

import github.wozniak.flighttrackingservice.airline_management.network_planner.enums.RouteFrequency;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.economics.entity.City;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static github.wozniak.flighttrackingservice.airline_management.network_planner.properties.NetworkConstraintProperties.PASSENGERS_PER_FLIGHT;

public class NetworkEconomicUtils {

    //Use city economics to determine an estimate for how many flights we could expect this city
    //to serve
    public static long estimatedFlightsPerYear(City origin){
        return origin.getEconomics().totalAveragePassengers() / PASSENGERS_PER_FLIGHT;
    }

    public static RouteFrequency routeFrequency(City origin){
        return switch (origin.getEconomics().getRanking()){
            case "1" -> RouteFrequency.DAILY;
            case "2", "3" -> RouteFrequency.WEEKLY;
            case "4" -> RouteFrequency.BI_WEEKLY;
            default -> RouteFrequency.MONTHLY;
        };
    }

    /*
    We want to get a basic idea of which airports are most important to include in our
        service network, so this uses a multitude of economic data to determine
        a profitable network order
     */
    public static List<Airport> sortAirportsByDemand(Map<String, City> cities,
                                                            List<Airport> airports){
        Map<Airport, Long> totalCAGR = new HashMap<>();
        airports.forEach(airport -> {
            if(!cities.containsKey(airport.getCityKey().toUpperCase().replace(" ", ""))) return;
            City city = cities.get(airport.getCityKey().toUpperCase().replace(" ", ""));
            long passengers = city.getEconomics().totalAveragePassengers();
            totalCAGR.put(airport, passengers);
        });
        return totalCAGR.entrySet().stream()
                .sorted(Map.Entry.<Airport, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }
}
