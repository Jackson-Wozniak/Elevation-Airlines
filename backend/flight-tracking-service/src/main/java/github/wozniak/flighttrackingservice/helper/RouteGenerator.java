package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.exception.RouteGeneratorException;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.utils.FlightDataCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class RouteGenerator {

    private final AirportService airportService;
    private static final Random random = new Random();

    public Route fromUnitedStates(Plane plane, double maxHours){
        Airport departure;
        List<Airport> airports = airportService.findAllAirports();
        List<Airport> airportsUnitedStates = airports.stream()
                .filter(airport -> airport.getCountry().equals("United States")).toList();
        if(airportsUnitedStates.size() > 0){
            departure = airportsUnitedStates.get(random.nextInt(airportsUnitedStates.size()));
        }else{
            departure = airports.get(random.nextInt(airports.size()));
        }
        airports.remove(departure);
        return createValidRoute(new ArrayList<>(airports), departure, plane, maxHours);
    }

    public Route fromAirport(Airport currentAirport, Plane plane, double maxHours){
        ArrayList<Airport> airports = new ArrayList<>(airportService.findAllAirports().stream()
                .filter(airport -> !airport.getIcaoCode().equals(currentAirport.getIcaoCode())).toList());
        return createValidRoute(airports, currentAirport, plane, maxHours);
    }

    private Route createValidRoute(ArrayList<Airport> airports, Airport departure, Plane plane, double maxHours){
        while(true){
            if(airports.size() == 0) throw new RouteGeneratorException("Could not create route");
            Airport destination = airports.get(random.nextInt(airports.size()));
            int distanceMiles = FlightDataCalculator.getFlightMiles(departure, destination);
            if(distanceMiles <= plane.getRangeMiles()){
                double flightHours = FlightDataCalculator.getFlightHours(distanceMiles, plane);
                if(flightHours > maxHours){
                    airports.remove(destination);
                    continue;
                }
                return new Route(
                        departure,
                        destination,
                        flightHours,
                        (double) distanceMiles
                );
            }
            airports.remove(destination);
        }
    }
}
