package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.helper.FlightCalendarCreator;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.model.TimeTable;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.FlightService;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.PlaneService;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.ScheduledRouteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class FlightCalendarCreatorTest {

    private final FlightCalendarCreator calendarCreator;
    private final PlaneService planeService;
    private final ScheduledRouteService scheduledRouteService;
    private final FlightService flightService;

    @Autowired
    public FlightCalendarCreatorTest(FlightCalendarCreator calendarCreator, PlaneService planeService, ScheduledRouteService routeService, FlightService flightService) {
        this.calendarCreator = calendarCreator;
        this.planeService = planeService;
        this.scheduledRouteService = routeService;
        this.flightService = flightService;
    }

    @Test
    void dateTime(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime dateTime = LocalDate.of(2023, 7, 4).atTime(9, 0);
        assertEquals("07/04/2023 09:00", dateTime.format(format));
    }

    @Test
    void flightsTimeTableUsesWholeFleet(){
        LocalDate today = flightService.findAllFlights().stream()
                .max(Comparator.comparing(Flight::getTakeOffDateTime))
                .orElse(new Flight()).getTakeOffDateTime().toLocalDate().plusDays(1);
        TimeTable timeTable = calendarCreator.createDaysTimeTable(
                today, scheduledRouteService.findDailySchedule());
        assertEquals(
                planeService.planeCount(),
                //filter flights today to gather unique planes
                timeTable.getFlightsToday().stream()
                        .map(Flight::getPlane).collect(Collectors.toSet()).size()
        );
        datesMatch(timeTable.getFlightsToday(), today);
    }

    void datesMatch(List<Flight> flights, LocalDate today){
        flights.forEach(flight -> assertFalse(flight.getTakeOffDateTime().toLocalDate().isAfter(today)));
    }

    @Test
    void thisTest(){
        test(flightService.findAllFlights());
    }

    void test(List<Flight> flights){
        Map<String, Integer> test = new HashMap<>();
        flights.forEach(flight -> {
            String callSign = flight.getPlane().getCallSign();
            if(test.containsKey(callSign)){
                test.put(callSign, test.get(callSign) + 1);
            }else{
                test.put(callSign, 1);
            }
        });
        Set<String> used = scheduledRouteService.findDailySchedule().stream().map(route -> route.getPlane().getCallSign()).collect(Collectors.toSet());
        HashMap<String, Integer> unscheduled = new HashMap<>();
        HashMap<String, Integer> scheduled = new HashMap<>();
        for(HashMap.Entry<String, Integer> entry : test.entrySet()){
            if(used.contains(entry.getKey())){
                scheduled.put(entry.getKey(), entry.getValue());
            }else{
                unscheduled.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println(unscheduled);
        System.out.println();
        System.out.println(scheduled);
    }
}
