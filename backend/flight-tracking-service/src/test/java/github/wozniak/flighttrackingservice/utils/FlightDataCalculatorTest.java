package github.wozniak.flighttrackingservice.utils;

import github.wozniak.flighttrackingservice.entity.Plane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightDataCalculatorTest {

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