package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Flight;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDTO {

    private long identifier;
    private RouteDTO route;
    private PlaneDTO plane;

    public FlightDTO(Flight flight){
        this.identifier = flight.getFlightIdentifier();
        this.route = new RouteDTO(flight.getRoute());
        this.plane = new PlaneDTO(flight.getPlane());
    }
}
