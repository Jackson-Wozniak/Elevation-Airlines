package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.ScheduledRoute;
import github.wozniak.flighttrackingservice.exception.RouteSchedulingException;
import github.wozniak.flighttrackingservice.repository.ScheduledRouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduledRouteService {

    private final ScheduledRouteRepository scheduledRouteRepository;
    private final PlaneService planeService;

    public void saveScheduledRoute(ScheduledRoute route){
        scheduledRouteRepository.save(route);
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
}
