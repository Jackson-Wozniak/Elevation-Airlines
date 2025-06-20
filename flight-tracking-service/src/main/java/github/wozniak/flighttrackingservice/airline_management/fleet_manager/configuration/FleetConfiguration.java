package github.wozniak.flighttrackingservice.airline_management.fleet_manager.configuration;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.FleetService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FleetConfiguration {
    private final FleetService fleetService;
    private static final Logger logger = LoggerFactory.getLogger(FleetConfiguration.class);

    public void configureFleet(){
        int fleetSize = fleetService.generateStartingFleet();
        logger.info("SAVING (" + fleetSize + ") PLANES TO FLEET");
    }
}
