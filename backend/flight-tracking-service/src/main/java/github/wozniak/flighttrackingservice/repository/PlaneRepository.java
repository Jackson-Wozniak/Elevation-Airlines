package github.wozniak.flighttrackingservice.repository;

import github.wozniak.flighttrackingservice.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, String> {
}
