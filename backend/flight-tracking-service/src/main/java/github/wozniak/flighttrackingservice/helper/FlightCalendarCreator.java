package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlightCalendarCreator {

    private final RouteGenerator routeGenerator;
    private final PlaneService planeService;
}
