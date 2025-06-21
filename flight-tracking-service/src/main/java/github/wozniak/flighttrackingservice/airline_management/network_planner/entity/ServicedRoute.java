package github.wozniak.flighttrackingservice.airline_management.network_planner.entity;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Route;
import github.wozniak.flighttrackingservice.airline_management.network_planner.enums.RouteFrequency;
import github.wozniak.flighttrackingservice.core.enums.TimeOfDay;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "servicedRoute")
@Table(name = "serviced_routes")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServicedRoute {
    @Id
    public String id;

    @Column(name = "flights_per_day")
    private Integer flightsPerInterval;

    @Enumerated(EnumType.STRING)
    @Column(name = "route_interval")
    private RouteFrequency routeInterval;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Embedded
    private Route route;

    @Column(name = "times_flown")
    private Long timesFlown;

    public ServicedRoute(int flightsPerInterval, RouteFrequency interval,
                         TimeOfDay time, Route route){
        this.id = route.getCodesKey();
        this.flightsPerInterval = flightsPerInterval;
        this.routeInterval = interval;
        this.timeOfDay = time;
        this.route = route;
        this.timesFlown = 0L;
    }
}
