package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @Modifying
    @Transactional
    public void deleteAllFlights(){
        flightRepository.deleteAll();
    }
}
