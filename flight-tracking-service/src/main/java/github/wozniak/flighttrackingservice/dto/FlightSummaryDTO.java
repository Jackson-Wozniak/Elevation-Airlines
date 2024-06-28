package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlightSummaryDTO {

    private long identifier;
    private RouteSummaryDTO route;
    private PlaneSummaryDTO plane;
    private String departureTime;
    private String estimatedTOA;
}
