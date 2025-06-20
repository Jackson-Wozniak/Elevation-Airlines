package github.wozniak.flighttrackingservice.core.configuration;

import github.wozniak.flighttrackingservice.core.data.CSVReader;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.core.service.PlaneModelService;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.FlightService;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.economics.service.CityService;
import github.wozniak.flighttrackingservice.economics.service.CountyService;
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
public class AirportConfiguration {

    private final AirportService airportService;
    private final FlightService flightService;
    private final ScheduledRouteService scheduledRouteService;
    private final PlaneModelService planeModelService;
    private final CityService cityService;
    private final CountyService countyService;
    private static final Logger logger = LoggerFactory.getLogger(AirportConfiguration.class);

    @PostConstruct
    public void configureAirports() throws IOException {
        logger.info("DELETE DATA ON STARTUP MODE: " + ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE);
        if(ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE){
            planeModelService.deleteAllModels();
            flightService.deleteAllFlights();
            scheduledRouteService.deleteAllRoutes();
            airportService.deleteAllAirports();
            cityService.deleteAllCities();
            countyService.deleteAllCounties();

            List<Airport> defaultAirports = CSVReader.airports();

            logger.info("SAVING (" + defaultAirports.size() + ") AIRPORTS");
            airportService.saveDefaultAirports(defaultAirports);
        }
    }
}
