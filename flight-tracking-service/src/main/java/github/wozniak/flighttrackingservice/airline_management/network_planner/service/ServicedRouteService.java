package github.wozniak.flighttrackingservice.airline_management.network_planner.service;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Route;
import github.wozniak.flighttrackingservice.airline_management.network_planner.entity.ServicedRoute;
import github.wozniak.flighttrackingservice.airline_management.network_planner.repository.ServicedRouteRepository;
import github.wozniak.flighttrackingservice.core.exception.RouteGeneratorException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ServicedRouteService {
    private final ServicedRouteRepository servicedRouteRepository;

    public ServicedRoute findServicedRoute(String id){
        return servicedRouteRepository.findById(id)
                .orElseThrow(() -> new RouteGeneratorException("Cannot find route"));
    }

    public boolean isServicedRoute(Route route){
        return servicedRouteRepository.findById(route.getCodesKey()).isPresent();
    }

    public long incrementServicedRoute(Flight flight){
        if(!isServicedRoute(flight.getRoute())) return 0L;

        ServicedRoute route = findServicedRoute(flight.getRoute().getCodesKey());
        route.incrementFlightsFlown();
        return servicedRouteRepository.save(route).getTimesFlown();
    }
}
