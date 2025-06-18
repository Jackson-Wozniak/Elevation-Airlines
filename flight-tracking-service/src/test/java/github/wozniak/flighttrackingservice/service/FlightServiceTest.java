package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.core.service.PlaneService;
import github.wozniak.flighttrackingservice.flight_management.entity.Flight;
import github.wozniak.flighttrackingservice.flight_management.service.FlightService;
import github.wozniak.flighttrackingservice.flight_management.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
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
    private final AirportService airportService;
    private final ScheduledRouteService scheduledRouteService;
    private final PlaneService planeService;

    @Autowired
    public FlightServiceTest(FlightService flightService, AirportService airportService, ScheduledRouteService scheduledRouteService, PlaneService planeService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.scheduledRouteService = scheduledRouteService;
        this.planeService = planeService;
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
                assertFalse(nextTakeOff.isBefore(landing.plusMinutes(30)));
            }
        });
    }

    @Test
    void liveFlightTest(){
        LocalDateTime time = LocalDateTime.now();
        flightService.findLiveFlights().forEach(flight -> {
            assertTrue(flight.getLandingDateTime().isAfter(time));
            assertTrue(flight.getTakeOffDateTime().isBefore(time));
        });
    }

    @Test
    void dateRangeTest(){
        String start = DateTimeUtils.format(LocalDate.now());
        String end = DateTimeUtils.format(LocalDate.now().plusDays(3));
        flightService.findFlightsByDateRange(start, end).forEach(timeTable -> {
            assertFalse(timeTable.getDate().isBefore(DateTimeUtils.toDate(start)));
            assertFalse(timeTable.getDate().isAfter(DateTimeUtils.toDate(end)));
        });
    }

    @Test
    void dateTest(){
        String today = DateTimeUtils.format(LocalDate.now());
        flightService.findFlightsByDate(today).forEach(flight -> {
            assertEquals(DateTimeUtils.toDate(today), flight.getTakeOffDateTime().toLocalDate());
        });
    }

    @Test
    void findFlightsByAirportTest(){
        String departure = scheduledRouteService.findDailySchedule().get(0).getRoute().getDepartureAirport().getIcaoCode();
        String destination = scheduledRouteService.findDailySchedule().get(0).getRoute().getDepartureAirport().getIcaoCode();
        flightService.findFlightsByAirport(departure, true, airportService).forEach(flight -> {
            assertEquals(flight.getRoute().getDepartureAirport().getIcaoCode(), departure);
        });

        flightService.findFlightsByAirport(destination, false, airportService).forEach(flight -> {
            assertEquals(flight.getRoute().getDestinationAirport().getIcaoCode(), destination);
        });
    }

    @Test
    void flightByIdentifier(){
        if(flightService.findAllFlights().isEmpty()) return;
        Flight flight = flightService.findAllFlights().get(0);
        assertDoesNotThrow(() -> flightService.findFlightsByIdentifier(flight.getFlightIdentifier()));
        assertEquals(flight.getFlightIdentifier(), flightService.findFlightsByIdentifier(flight.getFlightIdentifier()).getFlightIdentifier());
    }

    @Test
    void flightByCallSign(){
        if(flightService.findAllFlights().isEmpty()) return;
        String callSign = scheduledRouteService.findDailySchedule().get(0).getCallSign();
        assertDoesNotThrow(() -> flightService.findFlightByCallSign(callSign, planeService));
        flightService.findFlightByCallSign(callSign, planeService).forEach(flight -> {
            assertEquals(callSign, flight.getPlane().getCallSign());
        });
    }
}