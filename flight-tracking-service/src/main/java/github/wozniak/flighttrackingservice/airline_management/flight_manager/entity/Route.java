package github.wozniak.flighttrackingservice.airline_management.flight_manager.entity;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.Plane;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Route {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_icao")
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_icao")
    private Airport destinationAirport;

    @Column(name = "flight_distance_miles")
    private Double flightDistanceMiles;

    public Route(Airport departure, Airport destination) {
        this.departureAirport = departure;
        this.destinationAirport = destination;
        this.flightDistanceMiles = getFlightMiles();
    }

    //uses haversine formula to find distance between coordinates
    private double getFlightMiles(){
        double depLat = Math.toRadians(this.departureAirport.getLatitude());
        double depLong = Math.toRadians(this.departureAirport.getLongitude());
        double destLat = Math.toRadians(this.destinationAirport.getLatitude());
        double destLong = Math.toRadians(this.destinationAirport.getLongitude());
        double dLat = depLat - destLat;
        double dLong = depLong - destLong;

        double a = (Math.pow(Math.sin(dLat / 2), 2)) + Math.cos(depLat) * Math.cos(destLat)
                * (Math.pow(Math.sin(dLong / 2), 2));
        double c = 2 * Math.asin(Math.sqrt(a));
        return Double.parseDouble(String.format("%.02f", c * 3958.8));
    }

    public String getCodesKey(){
        return departureAirport.getIcaoCode() + " to " + destinationAirport.getIcaoCode();
    }
}
