package github.wozniak.flighttrackingservice.planning_algorithm;

import github.wozniak.flighttrackingservice.dto.AirportDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class FlightPathDTO {

    private final Set<AirportDTO> airportsVisited;
    private final Set<FlightSummaryDTO> flightPath;

    public FlightPathDTO(FlightPath flightPath){
        this.airportsVisited = flightPath.getAirportsVisited().stream().map(AirportDTO::new).collect(Collectors.toSet());
        this.flightPath = flightPath.getFlightPath().stream().map(FlightSummaryDTO::new).collect(Collectors.toSet());
    }
}
