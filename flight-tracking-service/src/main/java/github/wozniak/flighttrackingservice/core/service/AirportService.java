package github.wozniak.flighttrackingservice.core.service;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.exception.AirportNotFoundException;
import github.wozniak.flighttrackingservice.core.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> findAllAirports(){
        return airportRepository.findAll();
    }

    public List<Airport> findAirportsByCountry(String country){
        return airportRepository.findAll().stream()
                .filter(airport -> airport.getCountry().equals(country))
                .toList();
    }

    public Airport findAirportByICAO(String icao){
        return airportRepository.findById(icao).orElseThrow(() -> new AirportNotFoundException("Cannot find"));
    }

    @Modifying
    @Transactional
    public void deleteAllAirports(){
        airportRepository.deleteAll();
    }

    public void saveDefaultAirports(List<Airport> airports){
        airportRepository.saveAll(airports);
    }

    public long airportCount(){
        return airportRepository.count();
    }

    public boolean airportExists(String icaoCode){
        return airportRepository.findById(icaoCode).isPresent();
    }
}
