package github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity;

import github.wozniak.flighttrackingservice.core.entity.PlaneModel;
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
    @JoinColumn(name = "model_name", nullable = false)
    private PlaneModel model;

    public Plane(String callSign, PlaneModel planeModel){
        this.callSign = callSign;
        this.model = planeModel;
    }
}
