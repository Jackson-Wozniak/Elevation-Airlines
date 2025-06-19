package github.wozniak.flighttrackingservice.core.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/*
TODO:
    Runs AFTER FlightComponentConfig, calling economic and flight management configurators
        to load all data needed on startup
 */
@Configuration
@AllArgsConstructor
@Order(3)
public class ElevationAirlinesConfig {

    @PostConstruct
    public void configureOnStartup(){

    }
    /*
    private final ScheduledRouteService scheduledRouteService;
    private final RouteGenerator routeGenerator;
    private final FlightCalendarCreator calendarCreator;
    private final FlightService flightService;
    private static final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(RouteConfig.class);


    This method will ensure that 15 daily routes are saved to the database. Alongside these daily flights,
    the remaining planes will fly 1-2 random routes per day. This method will also ensure one week of flights
    are saved upon startup, and allow for the scheduler (FlightScheduler.java)
    to work into the future instead of needing to catch up on missed days caused by downtime
    discrepancies (flights are created at midnight with the scheduler,
        and therefore it is open to discrepancies if the application is not running at midnight)

    @PostConstruct
    public void configureScheduledRoutes(){
        //configure 15 scheduledRoute to fly the same time every day, based on plane callSign
        int remainingFlights = (int) scheduledRouteService.remainingRoutesToSchedule();
        if(remainingFlights > 0){
            scheduledRouteService.saveScheduledRoutes(createScheduledRoutes(remainingFlights));
        }
        fillMissingDaysInCalendar();
        flightService.deletePastFlights();
    }

    public List<ScheduledRoute> createScheduledRoutes(int routesToCreate){
        List<Plane> availablePlanes = scheduledRouteService.findAvailablePlanes();
        List<ScheduledRoute> scheduledRoutes = new ArrayList<>();
        for(int i = 0; i < routesToCreate; i++){
            if(availablePlanes.size() == 0) break;
            Plane plane = availablePlanes.get(random.nextInt(availablePlanes.size()));
            Route route = routeGenerator.fromUnitedStates(plane, 11);
            scheduledRoutes.add(new ScheduledRoute(plane, route, DateTimeUtils.createTimeOfFlight(11, true)));
            availablePlanes.remove(plane);
        }
        return scheduledRoutes;
    }

    public void fillMissingDaysInCalendar() {
        List<LocalDate> missingDates = calendarCreator.missingDays(LocalDate.now(), LocalDate.now().plusDays(7));
        if (!missingDates.isEmpty()) {
            logger.info(missingDates.size() + " missing dates: scheduling time tables");
            List<ScheduledRoute> scheduledRoutes = scheduledRouteService.findDailySchedule();
            for (LocalDate missingDate : missingDates) {
                flightService.saveFlights(
                        calendarCreator.createDaysTimeTable(missingDate, scheduledRoutes)
                                .getFlightsToday());
            }
        }
    }*/
}
