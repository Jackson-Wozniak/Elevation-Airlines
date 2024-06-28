package github.wozniak.flighttrackingservice.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void correctLandingTime(){
        Plane plane = new Plane("Test", "Test", 100, 110, 100, 1000);
        Airport boston = new Airport("Boston", "Test", 42.3601, 71.0589, "Test", "Test");
        Airport nyc = new Airport("NYC", "Test", 40.7128, 74.0060, "Test", "Test");
        Route route = new Route(boston, nyc, plane);

        LocalDateTime takeOffTime = LocalDateTime.now();
        Flight flight = new Flight(plane, route, takeOffTime);
        assertEquals(takeOffTime.plusMinutes((int) (1.65 * 60)), flight.getLandingDateTime());
    }

}