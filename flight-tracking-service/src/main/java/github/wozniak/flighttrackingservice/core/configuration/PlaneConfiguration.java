package github.wozniak.flighttrackingservice.core.configuration;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.entity.Plane;
import github.wozniak.flighttrackingservice.core.model.PlaneModel;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.core.service.PlaneService;
import github.wozniak.flighttrackingservice.core.utils.CSVReader;
import github.wozniak.flighttrackingservice.flight_management.service.FlightService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.List;

@Configuration
@AllArgsConstructor
@Order(2)
public class PlaneConfiguration {

    private final PlaneService planeService;
    private static final Logger logger = LoggerFactory.getLogger(PlaneConfiguration.class);

    @PostConstruct
    public void configurePlanes() throws IOException {
        if(ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE){
            List<PlaneModel> defaultPlanes = CSVReader.readAllPlaneModels();

            //TODO: as we migrate Planes to flight_management, this will only store PlaneModel info
            logger.info("SAVING (" + defaultPlanes.size() + ") PLANE MODELS (DEPRECATE IN FUTURE)");
            planeService.generateAndSavePlanes(defaultPlanes);
        }
    }
}
