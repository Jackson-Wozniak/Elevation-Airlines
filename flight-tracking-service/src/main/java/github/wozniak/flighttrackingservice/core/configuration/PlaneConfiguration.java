package github.wozniak.flighttrackingservice.core.configuration;

import github.wozniak.flighttrackingservice.core.data.CSVReader;
import github.wozniak.flighttrackingservice.core.entity.PlaneModel;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.PlaneModelService;
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

    private final PlaneModelService planeModelService;
    private static final Logger logger = LoggerFactory.getLogger(PlaneConfiguration.class);

    @PostConstruct
    public void configurePlanes() throws IOException {
        if(ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE){
            List<PlaneModel> defaultPlanes = CSVReader.planeModels();

            logger.info("SAVING (" + defaultPlanes.size() + ") PLANE MODELS");
            planeModelService.saveDefaultModels(defaultPlanes);
        }
    }
}
