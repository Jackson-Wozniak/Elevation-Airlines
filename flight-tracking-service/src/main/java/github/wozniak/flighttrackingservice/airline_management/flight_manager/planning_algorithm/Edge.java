package github.wozniak.flighttrackingservice.airline_management.flight_manager.planning_algorithm;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@EqualsAndHashCode
public class Edge  {
    private final AirportNode source;
    private final Flight flight;

    public Edge(AirportNode source, Flight flight) {
        this.source = source;
        this.flight = flight;
    }

    public String toString(){
        return format(this.getFlight().getTakeOffDateTime())
                + " : " + this.getFlight().getRoute().getDepartureAirport().getIcaoCode() + "->" + this.getFlight().getRoute().getDestinationAirport().getIcaoCode()
                + " : " + format(this.getFlight().getLandingDateTime());
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
    public String format(LocalDateTime dateTime){
        return dateTime.format(formatter);
    }
}
