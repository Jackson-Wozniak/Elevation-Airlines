package github.wozniak.flighttrackingservice.core.configuration;

import github.wozniak.flighttrackingservice.core.data.CSVReader;
import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.AircraftService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.List;

@Configuration
@AllArgsConstructor
@Order(2)
@DependsOn({"airportConfiguration"})
public class AircraftConfiguration {

    private final AircraftService aircraftService;
    private static final Logger logger = LoggerFactory.getLogger(AircraftConfiguration.class);

    @PostConstruct
    public void configureAircraft() throws IOException {
        if(ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE){
            List<Aircraft> defaultPlanes = CSVReader.planeModels();

            logger.info("SAVING (" + defaultPlanes.size() + ") PLANE MODELS");
            aircraftService.saveDefaultAircraft(defaultPlanes);
        }
    }
}
