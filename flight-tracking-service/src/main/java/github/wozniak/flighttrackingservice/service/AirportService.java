package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.exception.AirportNotFoundException;
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

    public void saveDefaultAirports(){
        airportRepository.saveAll(AirportsList.getDefaultAirports());
    }

    public long airportCount(){
        return airportRepository.count();
    }

    public boolean airportExists(String icaoCode){
        return airportRepository.findById(icaoCode).isPresent();
    }
}
