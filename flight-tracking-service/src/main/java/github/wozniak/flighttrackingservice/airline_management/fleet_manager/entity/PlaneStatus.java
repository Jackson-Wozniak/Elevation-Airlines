package github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.enums.PlaneState;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class PlaneStatus {
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private PlaneState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_code")
    private Airport mostRecentAirport;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

    public PlaneStatus(Airport startingPoint, LocalDateTime current){
        mostRecentAirport = startingPoint;
        state = PlaneState.AT_GATE;
        lastUpdatedAt = current;
    }

    public boolean inAir(){
        return state.equals(PlaneState.IN_FLIGHT);
    }
}
