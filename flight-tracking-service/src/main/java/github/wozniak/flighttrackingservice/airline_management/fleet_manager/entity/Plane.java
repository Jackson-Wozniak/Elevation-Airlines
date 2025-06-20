package github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity;

import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "plane")
@Table(name = "planes")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Plane {

    @Id
    @Column(name = "call_sign")
    private String callSign;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "aircraft_type", nullable = false)
    private Aircraft aircraft;

    @Embedded
    private PlaneStatus status;

    public Plane(int id, Aircraft aircraft, PlaneStatus status){
        this.callSign = ElevationAirlineProperties.CALLSIGN_PREFIX + " " + id;
        this.aircraft = aircraft;
        this.status = status;
    }
}
