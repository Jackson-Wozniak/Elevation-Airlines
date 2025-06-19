package github.wozniak.flighttrackingservice.airline_management.fleet_manager.repository;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, String> {
}
