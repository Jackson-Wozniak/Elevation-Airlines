package github.wozniak.flighttrackingservice.airline_management.flight_manager.dto;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
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
    private long identifer;
    private String departure;
    private String destination;
    private String callsign;
    private String planeType;
    private String scheduledBoarding;
    private String scheduledTakeoff;
    private String scheduledArrival;
    private double distanceMiles;

    public FlightSummaryDTO(Flight flight){
        this.identifer = flight.getFlightIdentifier();
        this.departure = flight.getRoute().getDepartureAirport().getIcaoCode();
        this.destination = flight.getRoute().getDestinationAirport().getIcaoCode();
        this.callsign = flight.getPlane().getCallSign();
        this.planeType = flight.getPlane().getAircraft().getName();
        this.scheduledBoarding = DateTimeUtils.format(flight.getScheduledBoardingTime());
        this.scheduledTakeoff = DateTimeUtils.format(flight.getScheduledDepartureTime());
        this.scheduledArrival = DateTimeUtils.format(flight.getScheduledLandingTime());
        this.distanceMiles = flight.getRoute().getFlightDistanceMiles();
    }
}
