package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
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
}
