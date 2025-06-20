package github.wozniak.flighttrackingservice.airline_management.fleet_manager.configuration;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.FleetService;
import github.wozniak.flighttrackingservice.core.data.CSVReader;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.County;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class FleetConfiguration {
    private final FleetService fleetService;
    private static final Logger logger = LoggerFactory.getLogger(FleetConfiguration.class);

    public void configureFleet(){
        if(ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE){
            int fleetSize = fleetService.generateStartingFleet();
            logger.info("SAVING (" + fleetSize + ") PLANES TO FLEET");
        }
    }
}
