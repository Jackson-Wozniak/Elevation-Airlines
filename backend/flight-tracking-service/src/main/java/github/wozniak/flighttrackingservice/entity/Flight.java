package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "flight")
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightIdentifier;

    @ManyToOne
    @JoinColumn(name = "call_sign")
    private Plane plane;

    @Embedded
    private Route route;

    @Column(name = "scheduled_takeoff_time_and_date")
    private LocalDateTime takeOffDateTime;

    public Flight(Plane plane, Route route, LocalDateTime takeOffDateTime){
        this.plane = plane;
        this.route = route;
        this.takeOffDateTime = takeOffDateTime;
    }

    public Flight(ScheduledRoute dailyScheduledRoute, LocalDateTime takeOffDateTime){
        this.plane = dailyScheduledRoute.getPlane();
        this.route = dailyScheduledRoute.getRoute();
        this.takeOffDateTime = takeOffDateTime;
    }
}
