package github.wozniak.flighttrackingservice.repository;

import github.wozniak.flighttrackingservice.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
