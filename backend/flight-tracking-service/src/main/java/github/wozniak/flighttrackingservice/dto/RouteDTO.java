package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDTO {

    private AirportDTO departure;
    private AirportDTO destination;
    private String flightTime;
    private double distanceMiles;

    public RouteDTO(Route route){
        this.departure = new AirportDTO(route.getDepartureAirport());
        this.destination = new AirportDTO(route.getDestinationAirport());
        this.flightTime = DateTimeUtils.hoursToHHMM(route.getFlightDurationHours());
        this.distanceMiles = route.getFlightDistanceMiles();
    }
}
