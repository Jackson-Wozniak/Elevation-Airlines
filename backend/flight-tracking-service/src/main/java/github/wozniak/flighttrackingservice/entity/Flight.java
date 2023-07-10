package github.wozniak.flighttrackingservice.entity;

import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public int getLandingHour(){
        //this could technically return a number greater than 24 so this has to be fixed later.
        //I currently have it this was because the current scheduling technically
        //allows for invalid flights to be dismissed, but this should be resolved later
        //so that flights aren't dismissed.
        return this.takeOffDateTime.plusMinutes((int) (this.route.getFlightDurationHours() * 60)).getHour() + 1;
    }

    public LocalDateTime getLandingDateTime(){
        return this.takeOffDateTime.plusMinutes((int) (this.route.getFlightDurationHours() * 60));
    }
}
