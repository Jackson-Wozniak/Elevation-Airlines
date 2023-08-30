package github.wozniak.flightbookingservice.service;

import github.wozniak.flightbookingservice.entity.Flight;
import github.wozniak.flightbookingservice.exception.FlightNotFoundException;
import github.wozniak.flightbookingservice.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Jackson Wozniak
 * @created : 8/30/2023, Wednesday
 **/
@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight findFlightById(long flightIdentifier){
        return flightRepository.findById(flightIdentifier)
                .orElseThrow(() -> new FlightNotFoundException("flight " + flightIdentifier + "doesn't exist"));
    }

    public List<Flight> findAvailableFlights(){
        return flightRepository.findAll().stream()
                .filter(flight -> flight.getSeatingInformation().getAvailableSeats() > 0)
                .toList();
    }
}
