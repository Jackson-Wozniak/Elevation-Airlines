package github.wozniak.flighttrackingservice.configuration;

import github.wozniak.flighttrackingservice.properties.AirportsList;
import github.wozniak.flighttrackingservice.properties.PlaneList;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@AllArgsConstructor
@Order(1)
public class FlightComponentConfig {

    private final PlaneService planeService;
    private final AirportService airportService;
    private static final Logger logger = LoggerFactory.getLogger(FlightComponentConfig.class);

    @PostConstruct
    public void configureFlightComponents(){
        if(planeService.planeCount() != 30){
            logger.info("Saving Default Planes");
            planeService.deleteAllPlanesAndFlights();
            planeService.saveDefaultPlanes();
        }
        if(AirportsList.getDefaultAirports().size() != airportService.airportCount()){
            logger.info("Saving Default Airports");
            airportService.deleteAllAirportsAndFlights();
            airportService.saveDefaultAirports();
        }
    }
}
