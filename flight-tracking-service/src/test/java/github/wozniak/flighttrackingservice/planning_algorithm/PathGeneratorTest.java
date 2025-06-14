package github.wozniak.flighttrackingservice.planning_algorithm;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.flight_management.entity.Flight;
import github.wozniak.flighttrackingservice.core.entity.Plane;
import github.wozniak.flighttrackingservice.flight_management.entity.Route;
import github.wozniak.flighttrackingservice.flight_management.planning_algorithm.Edge;
import github.wozniak.flighttrackingservice.flight_management.planning_algorithm.PathGenerator;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.flight_management.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PathGeneratorTest {

    private final FlightService flightService;
    private final PathGenerator pathGenerator;
    private final AirportService airportService;

    @Autowired
    public PathGeneratorTest(FlightService flightService, PathGenerator pathGenerator, AirportService airportService) {
        this.flightService = flightService;
        this.pathGenerator = pathGenerator;
        this.airportService = airportService;
    }

    @Test
    void validate(){
        Plane plane = new Plane("B737", "B737", 10, 10, 10, 10);
        List<Airport> airports = airportService.findAllAirports();
        Airport kbos = airports.stream().filter(a -> a.getIcaoCode().equals("KBOS")).findFirst().orElseThrow();
        Airport klga = airports.stream().filter(a -> a.getIcaoCode().equals("KLGA")).findFirst().orElseThrow();
        Airport lirp = airports.stream().filter(a -> a.getIcaoCode().equals("LIRP")).findFirst().orElseThrow();
        Airport eidw = airports.stream().filter(a -> a.getIcaoCode().equals("EIDW")).findFirst().orElseThrow();
        Airport olba = airports.stream().filter(a -> a.getIcaoCode().equals("OLBA")).findFirst().orElseThrow();
        Airport eddf = airports.stream().filter(a -> a.getIcaoCode().equals("EDDF")).findFirst().orElseThrow();
        List<Flight> flights = List.of(
                new Flight(plane, new Route(kbos, klga, plane), LocalDateTime.now()),
                new Flight(plane, new Route(klga, olba, plane), LocalDateTime.now().plusDays(2))
        );

        assertDoesNotThrow(() -> pathGenerator.uniquePath(flights, "KBOS", "OLBA"));
        List<Edge> path = pathGenerator.uniquePath(flights, "KBOS", "OLBA");

        assertEquals("KBOS", path.get(0).getFlight().getRoute().getDepartureAirport().getIcaoCode());
        assertEquals("OLBA", path.get(path.size() - 1).getFlight().getRoute().getDestinationAirport().getIcaoCode());
        for(int i = 0; i < path.size() - 1; i++){
            assertTrue(path.get(i).getFlight().getLandingDateTime()
                    .isBefore(path.get(i + 1).getFlight().getTakeOffDateTime()));
        }

        //test to make sure single flight path is found
        List<Flight> flights2 = List.of(
                new Flight(plane, new Route(kbos, klga, plane), LocalDateTime.now())
        );
        assertDoesNotThrow(() -> pathGenerator.uniquePath(flights2, "KBOS", "KLGA"));
        List<Edge> path2 = pathGenerator.uniquePath(flights2, "KBOS", "KLGA");
        assertEquals("KBOS", path2.get(0).getFlight().getRoute().getDepartureAirport().getIcaoCode());
        assertEquals("KLGA", path2.get(0).getFlight().getRoute().getDestinationAirport().getIcaoCode());

        assertEquals(1, path2.size());
    }

    @Test
    void complexValidation(){
        Plane plane = new Plane("B737", "B737", 10, 10, 500, 10);
        List<Airport> airports = airportService.findAllAirports();
        Airport kbos = airports.stream().filter(a -> a.getIcaoCode().equals("KBOS")).findFirst().orElseThrow();
        Airport klga = airports.stream().filter(a -> a.getIcaoCode().equals("KLGA")).findFirst().orElseThrow();
        Airport lirp = airports.stream().filter(a -> a.getIcaoCode().equals("LIRP")).findFirst().orElseThrow();
        Airport eidw = airports.stream().filter(a -> a.getIcaoCode().equals("EIDW")).findFirst().orElseThrow();
        Airport olba = airports.stream().filter(a -> a.getIcaoCode().equals("OLBA")).findFirst().orElseThrow();
        Airport eddf = airports.stream().filter(a -> a.getIcaoCode().equals("EDDF")).findFirst().orElseThrow();
        List<Flight> flights = List.of(
                new Flight(plane, new Route(kbos, klga, plane), LocalDateTime.now()), //1
                new Flight(plane, new Route(klga, olba, plane), LocalDateTime.now().plusDays(3)),
                new Flight(plane, new Route(klga, eidw, plane), LocalDateTime.now().plusDays(6)), //2
                new Flight(plane, new Route(eidw, lirp, plane), LocalDateTime.now().plusDays(9)), //3
                new Flight(plane, new Route(lirp, eddf, plane), LocalDateTime.now().plusDays(12)), //4
                new Flight(plane, new Route(eidw, lirp, plane), LocalDateTime.now().plusDays(15)),
                new Flight(plane, new Route(lirp, eidw, plane), LocalDateTime.now().plusDays(18))
        );

        assertDoesNotThrow(() -> pathGenerator.uniquePath(flights, "KBOS", "EDDF"));
        List<Edge> path = pathGenerator.uniquePath(flights, "KBOS", "EDDF");

        assertEquals("KBOS", path.get(0).getFlight().getRoute().getDepartureAirport().getIcaoCode());
        assertEquals("EDDF", path.get(path.size() - 1).getFlight().getRoute().getDestinationAirport().getIcaoCode());
        for(int i = 0; i < path.size() - 1; i++){
            assertTrue(path.get(i).getFlight().getLandingDateTime()
                    .isBefore(path.get(i + 1).getFlight().getTakeOffDateTime()));
        }
    }

}