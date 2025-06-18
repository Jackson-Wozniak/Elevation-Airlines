package github.wozniak.flighttrackingservice.core.repository;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
