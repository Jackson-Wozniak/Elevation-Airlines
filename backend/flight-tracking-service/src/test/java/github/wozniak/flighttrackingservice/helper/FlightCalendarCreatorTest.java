package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.model.FlightTimeTable;
import github.wozniak.flighttrackingservice.service.PlaneService;
import github.wozniak.flighttrackingservice.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class FlightCalendarCreatorTest {

    private final FlightCalendarCreator calendarCreator;
    private final PlaneService planeService;
    private final ScheduledRouteService scheduledRouteService;

    @Autowired
    public FlightCalendarCreatorTest(FlightCalendarCreator calendarCreator, PlaneService planeService, ScheduledRouteService routeService) {
        this.calendarCreator = calendarCreator;
        this.planeService = planeService;
        this.scheduledRouteService = routeService;
    }

    @Test
    void dateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalTime time = DateTimeUtils.createTimeOfFlight();
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), time);
        System.out.println(dateTime);
        assertEquals("07/04/2023 " + time, dateTime.format(format));
    }

    @Test
    void flightsTimeTableUsesWholeFleet(){
        LocalDate today = LocalDate.now();
        FlightTimeTable timeTable = calendarCreator.createDaysTimeTable(
                today, scheduledRouteService.findDailySchedule());
        assertEquals(
                planeService.planeCount(),
                timeTable.getFlightsToday().size()
        );
        datesMatch(timeTable.getFlightsToday(), today);
    }

    void datesMatch(List<Flight> flights, LocalDate today){
        flights.forEach(flight ->
                assertEquals(today, flight.getTakeOffDateTime().toLocalDate()));
    }
}
