package github.wozniak.flighttrackingservice.entity;

import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Route {

    @ManyToOne
    @JoinColumn(name = "departure_icao")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destination_icao")
    private Airport destinationAirport;

    @Column(name = "flight_duration_hours")
    private Double flightDurationHours;

    @Column(name = "flight_distance_miles")
    private Double flightDistanceMiles;

    public Route(Airport departure, Airport destination, Plane plane) {
        this.departureAirport = departure;
        this.destinationAirport = destination;
        this.flightDistanceMiles = getFlightMiles();
        this.flightDurationHours = getFlightHours(plane);
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

    private double getFlightHours(Plane plane){
        if(plane.getCruisingSpeedKnots() == 0) throw new RuntimeException("Plane must have positive cruise speed");
        double time = this.flightDistanceMiles / knotsToMPH(plane.getCruisingSpeedKnots());
        return Double.parseDouble(String.format("%.02f", time));
    }

    private static double knotsToMPH(int knots){
        return Double.parseDouble(String.format("%.02f", knots * 1.15078));
    }

    public String getFlightTime(){
        return DateTimeUtils.hoursToHHMM(flightDurationHours);
    }
}
