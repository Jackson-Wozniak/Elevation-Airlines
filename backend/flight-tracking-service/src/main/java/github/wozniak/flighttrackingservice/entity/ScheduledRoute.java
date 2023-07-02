package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity(name = "scheduledRoute")
@Table(name = "scheduled_routes")
@Getter
@Setter
@NoArgsConstructor
public class ScheduledRoute {

    @Id
    @Column(name = "call_sign_id")
    private String callSign;

    @OneToOne
    @JoinColumn(name = "call_sign")
    private Plane plane;

    @Embedded
    private Route route;

    @Column(name = "time_of_flight")
    private LocalTime time;

    public ScheduledRoute(Plane plane, Route route, LocalTime time){
        this.callSign = plane.getCallSign();
        this.plane = plane;
        this.route = route;
        this.time = time;
    }

    public boolean isPlaneScheduled(List<ScheduledRoute> currentRoutes){
        return currentRoutes.stream().anyMatch(route -> route.getPlane().equals(this.getPlane()));
    }
}
