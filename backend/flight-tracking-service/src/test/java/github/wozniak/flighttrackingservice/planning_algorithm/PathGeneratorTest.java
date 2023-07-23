package github.wozniak.flighttrackingservice.planning_algorithm;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
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