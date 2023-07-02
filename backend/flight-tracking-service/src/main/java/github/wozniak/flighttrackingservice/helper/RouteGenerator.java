package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RouteGenerator {

    private final AirportService airportService;
}
