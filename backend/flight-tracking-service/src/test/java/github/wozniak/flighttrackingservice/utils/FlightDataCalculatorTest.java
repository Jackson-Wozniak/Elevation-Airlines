package github.wozniak.flighttrackingservice.utils;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Plane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightDataCalculatorTest {

    @Test
    void calculateDistanceTest(){
        Airport boston = new Airport("Boston", "Test", 42.3601, 71.0589, "Test", "Test");
        Airport nyc = new Airport("NYC", "Test", 40.7128, 74.0060, "Test", "Test");
        Airport london = new Airport("London", "Test", 51.5072, 0.1276, "Test", "Test");
        assertEquals(190, FlightDataCalculator.getFlightMiles(boston, nyc));
        assertEquals(3271, FlightDataCalculator.getFlightMiles(boston, london));
        assertEquals(3461, FlightDataCalculator.getFlightMiles(nyc, london));
    }

    @Test
    void knotsToMPHTest(){
        assertEquals(0.00, FlightDataCalculator.knotsToMPH(0));
        assertEquals(115.08, FlightDataCalculator.knotsToMPH(100));
    }

    @Test
    void flightHoursCalculatorTest(){
        Plane zeroSpeedPlane = new Plane("Test", "Test", 100, 10, 0, 0);
        assertThrows(RuntimeException.class, () -> FlightDataCalculator.getFlightHours(1000, zeroSpeedPlane));

        Plane plane = new Plane("Test", "Test", 100, 10, 100, 10000);
        assertEquals(0, FlightDataCalculator.getFlightHours(0, plane));
        assertEquals(8.69, FlightDataCalculator.getFlightHours(1000, plane));
        assertEquals(7.61, FlightDataCalculator.getFlightHours(876, plane));
    }

}