package github.wozniak.flighttrackingservice.flight_management.dto;

import github.wozniak.flighttrackingservice.core.dto.AirportDTO;
import github.wozniak.flighttrackingservice.flight_management.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteDTO {

    private AirportDTO departure;
    private AirportDTO destination;
    private String flightTime;
    private double distanceMiles;

    public RouteDTO(Route route){
        this.departure = new AirportDTO(route.getDepartureAirport());
        this.destination = new AirportDTO(route.getDestinationAirport());
        this.flightTime = route.getFlightTime();
        this.distanceMiles = route.getFlightDistanceMiles();
    }
}
