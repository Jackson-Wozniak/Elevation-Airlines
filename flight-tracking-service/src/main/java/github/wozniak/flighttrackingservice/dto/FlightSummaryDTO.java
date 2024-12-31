package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSummaryDTO {
    private String departure;
    private String destination;
    private String callsign;
    private String planeType;
    private String departureTime;
    private String flightTime;
    private double distanceMiles;

    public FlightSummaryDTO(Flight flight){
        this.departure = flight.getRoute().getDepartureAirport().getIcaoCode();
        this.destination = flight.getRoute().getDestinationAirport().getIcaoCode();
        this.callsign = flight.getPlane().getCallSign();
        this.planeType = flight.getPlane().getModel();
        this.departureTime = DateTimeUtils.format(flight.getTakeOffDateTime());
        this.flightTime = DateTimeUtils.hoursToHRMIN(flight.getRoute().getFlightDurationHours());
        this.distanceMiles = flight.getRoute().getFlightDistanceMiles();
    }
}
