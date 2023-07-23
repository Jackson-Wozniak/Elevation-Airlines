package github.wozniak.flighttrackingservice.planning_algorithm;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class FlightPath {
    private Set<Airport> airportsVisited;
    private Set<Flight> flightPath;
}
