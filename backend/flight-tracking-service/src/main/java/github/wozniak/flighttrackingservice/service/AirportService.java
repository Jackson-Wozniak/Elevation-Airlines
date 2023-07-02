package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.properties.AirportsList;
import github.wozniak.flighttrackingservice.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final FlightService flightService;

    public List<Airport> findAllAirports(){
        return airportRepository.findAll();
    }

    @Modifying
    @Transactional
    public void deleteAllAirportsAndFlights(){
        airportRepository.deleteAll();
        flightService.deleteAllFlights();
    }

    public void saveDefaultAirports(){
        airportRepository.saveAll(AirportsList.getDefaultAirports());
    }

    public long airportCount(){
        return airportRepository.count();
    }
}