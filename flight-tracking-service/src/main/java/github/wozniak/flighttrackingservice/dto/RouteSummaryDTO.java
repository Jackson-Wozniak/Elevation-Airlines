package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteSummaryDTO {

    private AirportSummaryDTO departure;
    private AirportSummaryDTO destination;
    private String flightTime;
    private double distanceMiles;
}
