package github.wozniak.flighttrackingservice.flight_management.service;

import github.wozniak.flighttrackingservice.core.entity.Plane;
import github.wozniak.flighttrackingservice.flight_management.entity.ScheduledRoute;
import github.wozniak.flighttrackingservice.flight_management.repository.ScheduledRouteRepository;
import github.wozniak.flighttrackingservice.core.service.PlaneService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduledRouteService {

    private final ScheduledRouteRepository scheduledRouteRepository;
    private final PlaneService planeService;

    public List<ScheduledRoute> findDailySchedule(){
        return scheduledRouteRepository.findAll();
    }

    public void saveScheduledRoute(ScheduledRoute route){
        scheduledRouteRepository.save(route);
    }

    public void saveScheduledRoutes(List<ScheduledRoute> routes){
        scheduledRouteRepository.saveAll(routes);
    }

    public List<Plane> findAvailablePlanes(){
        List<Plane> planes = new ArrayList<>(scheduledRouteRepository.findAll().stream().map(ScheduledRoute::getPlane).toList());
        List<Plane> availablePlanes = planeService.findAllPlanes();
        availablePlanes.removeAll(planes);
        return availablePlanes;
    }

    public long remainingRoutesToSchedule(){
        List<ScheduledRoute> routes = scheduledRouteRepository.findAll();
        if(routes.size() >= 15) return 0;
        return 15 - routes.size();
    }

    @Modifying
    @Transactional
    public void deleteAllRoutes(){
        scheduledRouteRepository.deleteAll();
    }
}
