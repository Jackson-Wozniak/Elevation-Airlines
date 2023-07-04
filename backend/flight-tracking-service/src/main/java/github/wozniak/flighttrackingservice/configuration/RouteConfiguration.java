package github.wozniak.flighttrackingservice.configuration;

import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.entity.ScheduledRoute;
import github.wozniak.flighttrackingservice.helper.RouteGenerator;
import github.wozniak.flighttrackingservice.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.utils.FlightDataCalculator;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@AllArgsConstructor
@Order(2)
public class RouteConfiguration {

    private final ScheduledRouteService scheduledRouteService;
    private final RouteGenerator routeGenerator;
    private static final Random random = new Random();

    /*
    This method will ensure that 15 daily routes are saved to the database. Alongside these daily flights,
    the remaining planes will fly 1-2 random routes per day. This method will also ensure one week of flights
    are saved upon startup, and allow for the scheduler (FlightScheduler.java)
    to work into the future instead of needing to catch up on missed days caused by downtime
    discrepancies (flights are created at midnight with the scheduler,
        and therefore it is open to discrepancies if the application is not running at midnight)
     */
    @PostConstruct
    public void configureScheduledRoutes(){
        //configure 15 scheduledRoute to fly the same time every day, based on plane callSign
        int remainingFlights = (int) scheduledRouteService.remainingRoutesToSchedule();
        if(remainingFlights > 0){
            scheduledRouteService.saveScheduledRoutes(createScheduledRoutes(remainingFlights));
        }
        //TODO: ensure a week of flights are scheduled, if not schedule them. Remove past flights
    }

    public List<ScheduledRoute> createScheduledRoutes(int routesToCreate){
        List<Plane> availablePlanes = scheduledRouteService.findAvailablePlanes();
        List<ScheduledRoute> scheduledRoutes = new ArrayList<>();
        for(int i = 0; i < routesToCreate; i++){
            if(availablePlanes.size() == 0) break;
            Plane plane = availablePlanes.get(random.nextInt(availablePlanes.size()));
            Route route = routeGenerator.flightFromUnitedStates(plane, 11);
            scheduledRoutes.add(new ScheduledRoute(plane, route, FlightDataCalculator.createTimeOfFlight()));
            availablePlanes.remove(plane);
        }
        return scheduledRoutes;
    }
}
