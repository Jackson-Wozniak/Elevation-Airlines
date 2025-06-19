package github.wozniak.flighttrackingservice.airline_management.flight_manager;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.helper.FlightCalendarCreator;
import github.wozniak.flighttrackingservice.core.properties.SchedulingProperties;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.FlightService;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@EnableScheduling
@Service
@AllArgsConstructor
public class FlightScheduler {

    private final FlightCalendarCreator flightCalendarCreator;
    private final FlightService flightService;
    private final ScheduledRouteService scheduledRouteService;
    private static final Logger logger = LoggerFactory.getLogger(FlightScheduler.class);

    /*
    RouteConfiguration.java is called on app startup to ensure flights are scheduled 1 week out,
    meaning that even if the app is not running when @Scheduled is expected to run, the airline
    calendar will always be filled with one week of flights
     */
    @Scheduled(cron = SchedulingProperties.runAtMidnight)
    public void scheduleFlightsOneWeekOut(){
        LocalDate oneWeek = LocalDate.now().plusDays(7);
        if(flightCalendarCreator.isDayMissing(oneWeek)){
            logger.info("Generating time table for " + DateTimeUtils.format(oneWeek));
            flightService.saveFlights(flightCalendarCreator.createDaysTimeTable(
                    oneWeek, scheduledRouteService.findDailySchedule()).getFlightsToday());
        }
        flightService.deletePastFlights();
    }
}
