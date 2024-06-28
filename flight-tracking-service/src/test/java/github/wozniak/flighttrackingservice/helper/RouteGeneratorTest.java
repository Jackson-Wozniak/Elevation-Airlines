package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.exception.RouteGeneratorException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RouteGeneratorTest {

    private final RouteGenerator routeGenerator;

    @Autowired
    RouteGeneratorTest(RouteGenerator routeGenerator) {
        this.routeGenerator = routeGenerator;
    }

    @Test
    void validUnitedStatesFlightTest(){
        Plane plane = new Plane("Test", "Test", 1000, 10, 400, 2300);
        Route route = routeGenerator.fromUnitedStates(plane, 10.5);
        assertEquals("United States", route.getDepartureAirport().getCountry());
        assertTrue(route.getFlightDistanceMiles() <= plane.getRangeMiles());
        assertTrue(route.getFlightDurationHours() <= 10.5);
    }

    @Test
    void noRangeThrowsErrorTest(){
        Plane plane = new Plane("Test", "Test", 1000, 10, 400, 0);
        assertThrows(RouteGeneratorException.class, () -> routeGenerator.fromUnitedStates(plane, 100));
    }

}