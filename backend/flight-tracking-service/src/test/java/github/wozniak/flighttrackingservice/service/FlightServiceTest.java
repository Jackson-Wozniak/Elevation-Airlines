package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class FlightServiceTest {

    private final FlightService flightService;

    @Autowired
    public FlightServiceTest(FlightService flightService) {
        this.flightService = flightService;
    }

    @Test
    void noScheduleDiscrepancies(){
        List<Flight> flights = flightService.findAllFlights();
        flights.stream().map(Flight::getPlane).collect(Collectors.toSet()).forEach(plane -> {
            Queue<Flight> flightsByPlane = flights.stream()
                    .filter(planes -> planes.getPlane().equals(plane))
                    .sorted(Comparator.comparing(Flight::getTakeOffDateTime))
                    .collect(Collectors.toCollection(LinkedList::new));
            while(flightsByPlane.peek() != null){
                Flight flight = flightsByPlane.poll();
                LocalDateTime landing = flight.getTakeOffDateTime().plusMinutes((int) (flight.getRoute().getFlightDurationHours() * 60));
                if(flightsByPlane.peek() == null) continue;
                LocalDateTime nextTakeOff = flightsByPlane.peek().getTakeOffDateTime();
                if(nextTakeOff.isBefore(landing)){
                    System.out.println("Call Sign: " + flight.getPlane().getCallSign());
                    System.out.println("Takeoff time: " + DateTimeUtils.format(flight.getTakeOffDateTime()));
                    System.out.println("Landing time: " + DateTimeUtils.format(landing));
                    System.out.println("Next Takeoff time: " + DateTimeUtils.format(nextTakeOff));
                    System.out.println();
                }
                assertFalse(nextTakeOff.isBefore(landing));
            }
        });
    }
}