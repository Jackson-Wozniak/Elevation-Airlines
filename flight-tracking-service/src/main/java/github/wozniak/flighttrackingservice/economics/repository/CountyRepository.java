package github.wozniak.flighttrackingservice.economics.repository;

import github.wozniak.flighttrackingservice.economics.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, String> {
}
