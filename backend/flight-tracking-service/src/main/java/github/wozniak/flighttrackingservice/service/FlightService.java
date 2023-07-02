package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightService {

    @Autowired
    private final FlightRepository flightRepository;

    @Modifying
    @Transactional
    public void deleteAllFlights(){
        flightRepository.deleteAll();
    }
}
