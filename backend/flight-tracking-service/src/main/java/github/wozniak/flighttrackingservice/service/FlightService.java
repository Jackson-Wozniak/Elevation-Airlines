package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public void saveFlights(List<Flight> flights){
        flightRepository.saveAll(flights);
    }

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    @Modifying
    @Transactional
    public void deleteAllFlights(){
        flightRepository.deleteAll();
    }
}
