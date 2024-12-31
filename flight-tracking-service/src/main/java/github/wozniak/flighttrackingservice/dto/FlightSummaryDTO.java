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

    public FlightSummaryDTO(Flight flight){
        this.identifier = flight.getFlightIdentifier();
        this.route = new RouteSummaryDTO(flight.getRoute());
        this.plane = new PlaneSummaryDTO(flight.getPlane());
        this.departureTime = DateTimeUtils.format(flight.getTakeOffDateTime());
        this.estimatedTOA = DateTimeUtils.format(flight.getLandingDateTime());
    }
}
