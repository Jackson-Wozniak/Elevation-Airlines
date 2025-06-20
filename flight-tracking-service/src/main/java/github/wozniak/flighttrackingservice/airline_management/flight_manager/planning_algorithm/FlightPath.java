package github.wozniak.flighttrackingservice.airline_management.flight_manager.planning_algorithm;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class FlightPath {
    private Set<Airport> airportsVisited;
    private Set<Flight> flightPath;
}
