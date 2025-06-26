package github.wozniak.flighttrackingservice.airline_management.flight_manager.scheduling.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteConstraints {
    private int maxRangeMiles;
    private int minTakeoffRunwayFt;
    private int minLandingRunwayFt;
}
