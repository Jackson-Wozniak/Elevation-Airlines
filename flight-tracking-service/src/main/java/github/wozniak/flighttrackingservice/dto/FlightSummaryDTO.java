package github.wozniak.flighttrackingservice.dto;

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
