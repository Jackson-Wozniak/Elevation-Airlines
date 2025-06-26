package github.wozniak.flighttrackingservice.airline_management.flight_manager.entity;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.Plane;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "flight")
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"plane", "route", "takeOffDateTime"})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightIdentifier;

    @ManyToOne
    @JoinColumn(name = "call_sign")
    private Plane plane;

    @Embedded
    private Route route;

    @Column(name = "scheduled_boarding_time")
    private LocalDateTime scheduledBoardingTime;

    public Flight(Plane plane, Route route, LocalDateTime scheduledBoardingTime){
        this.plane = plane;
        this.route = route;
        this.scheduledBoardingTime = scheduledBoardingTime;
    }

    public LocalDateTime getScheduledDepartureTime(){
        return scheduledBoardingTime.plusMinutes(ElevationAirlineProperties.BOARDING_MINUTES);
    }

    public double getFlightHours(){
        double time = this.route.getFlightDistanceMiles()
                / knotsToMPH(plane.getAircraft().getCruisingSpeedKnots());
        return Double.parseDouble(String.format("%.02f", time));
    }

    private static double knotsToMPH(int knots){
        return Double.parseDouble(String.format("%.02f", knots * 1.15078));
    }

    public String getFlightTime(){
        return DateTimeUtils.hoursToHHMM(getFlightHours());
    }

    public LocalDateTime getScheduledLandingTime(){
        return DateTimeUtils.plusHours(getScheduledDepartureTime()
                .plusMinutes(ElevationAirlineProperties.TAXI_MINUTES), getFlightHours());
    }

    public boolean isMatchingAirport(String icao, boolean departureQuery){
        if(departureQuery){
            return this.getRoute().getDepartureAirport().getIcaoCode().equals(icao);
        }
        return this.getRoute().getDestinationAirport().getIcaoCode().equals(icao);
    }

    @Override
    public String toString(){
        return this.getRoute().getDepartureAirport().getIcaoCode() + "->" + this.getRoute().getDestinationAirport().getIcaoCode();
    }
}
