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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_icao", referencedColumnName = "icaoCode")
    private Airport departureAirport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_icao", referencedColumnName = "icaoCode")
    private Airport destinationAirport;

    @Column(name = "flightDurationHours")
    private Double flightDurationHours;

    @Column(name = "flightDistanceMiles")
    private Double flightDistanceMiles;
}
