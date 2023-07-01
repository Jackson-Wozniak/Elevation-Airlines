package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "flight")
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightIdentifier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "call_sign", referencedColumnName = "call_sign")
    private Plane plane;

    @Embedded
    private Route route;

    public Flight(Plane plane, Route route){
        this.plane = plane;
        this.route = route;
    }
}
