package github.wozniak.flighttrackingservice.configuration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RouteConfigurationTest {

    private final RouteConfiguration routeConfiguration;

    @Autowired
    RouteConfigurationTest(RouteConfiguration routeConfiguration) {
        this.routeConfiguration = routeConfiguration;
    }

    @Test
    void scheduledRouteCreationTest(){
        routeConfiguration.createScheduledRoutes(100).forEach(scheduled -> {
            assertTrue(scheduled.getRoute().getFlightDurationHours() < 11);
            assertTrue(scheduled.getPlane().getRangeMiles() >= scheduled.getRoute().getFlightDistanceMiles());
        });

    }
}