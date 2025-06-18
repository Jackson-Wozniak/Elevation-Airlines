package github.wozniak.flighttrackingservice.flight_management.dto;

import github.wozniak.flighttrackingservice.flight_management.entity.Flight;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

/*
This class is used as a way to summarize all flight information, and is most
    relevant for returning to flight tables and other displays that contain many flights
    and only basic info is needed for each one
 */
@Getter
@Setter
public class FlightSummaryDTO {
    private String departure;
    private String destination;
    private String callsign;
    private String planeType;
    private String timeOfDeparture;
    private String expectedArrival;
    private double distanceMiles;

    public FlightSummaryDTO(Flight flight){
        this.departure = flight.getRoute().getDepartureAirport().getIcaoCode();
        this.destination = flight.getRoute().getDestinationAirport().getIcaoCode();
        this.callsign = flight.getPlane().getCallSign();
        this.planeType = flight.getPlane().getModel();
        this.timeOfDeparture = DateTimeUtils.format(flight.getTakeOffDateTime());
        this.expectedArrival = DateTimeUtils.expectedTimeOfArrival(flight.getTakeOffDateTime(), flight.getRoute().getFlightDurationHours());
        this.distanceMiles = flight.getRoute().getFlightDistanceMiles();
    }
}
