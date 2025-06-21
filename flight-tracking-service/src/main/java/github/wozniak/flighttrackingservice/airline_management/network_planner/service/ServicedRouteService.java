package github.wozniak.flighttrackingservice.airline_management.network_planner.service;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Route;
import github.wozniak.flighttrackingservice.airline_management.network_planner.repository.ServicedRouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ServicedRouteService {
    private final ServicedRouteRepository servicedRouteRepository;

    public boolean isServicedRoute(Route route){
        return servicedRouteRepository.findById(route.getCodesKey()).isPresent();
    }

    public long incrementServicedRoute(Flight flight){

    }
}
