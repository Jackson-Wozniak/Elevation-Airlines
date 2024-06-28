package github.wozniak.flighttrackingservice.properties;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.service.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class AirportsListTest {

    private final AirportService airportService;

    @Autowired
    AirportsListTest(AirportService airportService) {
        this.airportService = airportService;
    }

    @Test
    void defaultAirportsMatchDatabase(){
        List<Airport> databaseAirports = airportService.findAllAirports();
        System.out.println(databaseAirports.size());
        List<String> defaultAirports = AirportsList.getDefaultAirports().stream()
                .map(Airport::getIcaoCode)
                .toList();
        assertEquals(databaseAirports.size(), defaultAirports.size());
        databaseAirports.forEach(airport ->
                assertTrue(defaultAirports.contains(airport.getIcaoCode())));
    }
}