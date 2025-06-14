package github.wozniak.flighttrackingservice.core.repository;

import github.wozniak.flighttrackingservice.core.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, String> {
}
