package github.wozniak.flighttrackingservice.core.repository;

import github.wozniak.flighttrackingservice.core.entity.PlaneModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneModelRepository extends JpaRepository<PlaneModel, String> {
}
