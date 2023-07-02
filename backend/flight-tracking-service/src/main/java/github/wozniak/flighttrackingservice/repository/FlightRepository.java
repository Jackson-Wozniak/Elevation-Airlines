package github.wozniak.flighttrackingservice.repository;

import github.wozniak.flighttrackingservice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
