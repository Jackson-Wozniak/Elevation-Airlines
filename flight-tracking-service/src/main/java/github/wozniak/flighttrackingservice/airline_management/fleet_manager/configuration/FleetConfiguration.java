package github.wozniak.flighttrackingservice.airline_management.fleet_manager.configuration;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.FleetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FleetConfiguration {
    private final FleetService fleetService;
}
