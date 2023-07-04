package github.wozniak.flighttrackingservice.scheduler;

import github.wozniak.flighttrackingservice.properties.SchedulingProperties;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
@AllArgsConstructor
public class FlightScheduler {

    /*
    RouteConfiguration.java is called on app startup to ensure flights are scheduled 1 week out,
    meaning that even if the app is not running when @Scheduled is expected to run, the airline
    calendar will always be filled with one week of flights
     */
    @Scheduled(cron = SchedulingProperties.runAtMidnight)
    public void scheduleFlightsOneWeekOut(){
        /*
        TODO:
         schedule 1 day of flights exactly 7 days from the current date.
         15 daily routes will be saved as well scheduling 1 or 2 flights for
         the remaining unused planes in the fleet. This method will also remove past flights
        */
    }
}
