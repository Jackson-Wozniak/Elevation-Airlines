package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlightDTO {

    private long identifier;
    private RouteDTO route;
    private PlaneDTO plane;
    private String departureTime;
    private String estimatedTOA;

    public FlightDTO(Flight flight){
        this.identifier = flight.getFlightIdentifier();
        this.route = new RouteDTO(flight.getRoute());
        this.plane = new PlaneDTO(flight.getPlane());
        this.departureTime = DateTimeUtils.format(flight.getTakeOffDateTime());
        this.estimatedTOA = DateTimeUtils.format(flight.getLandingDateTime());
    }
}
