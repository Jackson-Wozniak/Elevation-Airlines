package github.wozniak.flighttrackingservice.economics.repository;

import github.wozniak.flighttrackingservice.economics.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
