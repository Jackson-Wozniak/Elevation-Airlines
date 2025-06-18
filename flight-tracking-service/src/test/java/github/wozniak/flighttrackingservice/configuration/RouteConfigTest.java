package github.wozniak.flighttrackingservice.configuration;

import github.wozniak.flighttrackingservice.core.configuration.RouteConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RouteConfigTest {

    private final RouteConfig routeConfig;

    @Autowired
    RouteConfigTest(RouteConfig routeConfig) {
        this.routeConfig = routeConfig;
    }

    @Test
    void scheduledRouteCreationTest(){
        routeConfig.createScheduledRoutes(100).forEach(scheduled -> {
            assertTrue(scheduled.getRoute().getFlightDurationHours() < 11);
            assertTrue(scheduled.getPlane().getRangeMiles() >= scheduled.getRoute().getFlightDistanceMiles());
        });

    }
}