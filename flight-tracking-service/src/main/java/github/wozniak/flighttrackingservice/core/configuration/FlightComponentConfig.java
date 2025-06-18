package github.wozniak.flighttrackingservice.core.configuration;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.model.PlaneModel;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.flight_management.service.FlightService;
import github.wozniak.flighttrackingservice.core.service.PlaneService;
import github.wozniak.flighttrackingservice.core.utils.CSVReader;
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
