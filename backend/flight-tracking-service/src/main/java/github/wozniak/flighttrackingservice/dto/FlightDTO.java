package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDTO {

    private long identifier;
    private RouteDTO route;
    private PlaneDTO plane;
    private String takeOffDateTime;

    public FlightDTO(Flight flight){
        this.identifier = flight.getFlightIdentifier();
        this.route = new RouteDTO(flight.getRoute());
        this.plane = new PlaneDTO(flight.getPlane());
        this.takeOffDateTime = DateTimeUtils.format(flight.getTakeOffDateTime());
    }
}
