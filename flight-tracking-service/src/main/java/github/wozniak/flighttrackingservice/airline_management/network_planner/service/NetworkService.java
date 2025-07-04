package github.wozniak.flighttrackingservice.airline_management.network_planner.service;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NetworkService {
    private final AirportService airportService;
    private final ServicedRouteService servicedRouteService;

    public Airport findPrimaryHub(){
        return airportService.findAirportByICAO(ElevationAirlineProperties.PRIMARY_HUB_CODE);
    }

    public List<Airport> findHubs(String name){
        throw new RuntimeException("Use findPrimaryHubs(), 1 hub total");
    }

    public long servicedRouteFlown(Flight flight){
        return servicedRouteService.incrementServicedRoute(flight);
    }
}
