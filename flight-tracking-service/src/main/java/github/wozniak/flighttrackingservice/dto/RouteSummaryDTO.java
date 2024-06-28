package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteSummaryDTO {

    private AirportSummaryDTO departure;
    private AirportSummaryDTO destination;
    private String flightTime;
    private double distanceMiles;

    public RouteSummaryDTO(Route route){
        this.departure = new AirportSummaryDTO(route.getDepartureAirport());
        this.destination = new AirportSummaryDTO(route.getDestinationAirport());
        this.flightTime = DateTimeUtils.hoursToHHMM(route.getFlightDurationHours());
        this.distanceMiles = route.getFlightDistanceMiles();
    }
}
