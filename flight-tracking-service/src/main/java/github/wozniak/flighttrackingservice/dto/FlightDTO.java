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
    private String timeOfDeparture;
    private String expectedArrival;
    private String estimatedTOA;

    public FlightDTO(Flight flight){
        this.identifier = flight.getFlightIdentifier();
        this.route = new RouteDTO(flight.getRoute());
        this.plane = new PlaneDTO(flight.getPlane());
        this.timeOfDeparture = DateTimeUtils.format(flight.getTakeOffDateTime());
        this.expectedArrival = DateTimeUtils.expectedTimeOfArrival(flight.getTakeOffDateTime(), flight.getRoute().getFlightDurationHours());
        this.estimatedTOA = DateTimeUtils.format(flight.getLandingDateTime());
    }
}
