package github.wozniak.flighttrackingservice.configuration;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.model.PlaneModel;
import github.wozniak.flighttrackingservice.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import github.wozniak.flighttrackingservice.utils.CSVReader;
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
@Order(1)
public class FlightComponentConfig {

    private final PlaneService planeService;
    private final AirportService airportService;
    private final FlightService flightService;
    private static final Logger logger = LoggerFactory.getLogger(FlightComponentConfig.class);

    @PostConstruct
    public void configureFlightComponents() throws IOException {
        List<Airport> airports = CSVReader.readAllAirports();
        List<PlaneModel> models = CSVReader.readAllPlaneModels();

        if(airports.size() != airportService.airportCount() || ElevationAirlineProperties.FLEET_COUNT != planeService.planeCount()){
            logger.info("Database inconsistency noted on startup. Recreating new flights");
            flightService.deleteAllFlights();
            logger.info("Saving Default Planes");
            planeService.deleteAllPlanes();
            planeService.generateAndSavePlanes(models);

            logger.info("Saving Default Airports");
            airportService.deleteAllAirports();
            airportService.saveDefaultAirports(airports);
        }
    }
}
