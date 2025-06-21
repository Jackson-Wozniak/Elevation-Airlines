package github.wozniak.flighttrackingservice.airline_management.network_planner.repository;

import github.wozniak.flighttrackingservice.airline_management.network_planner.entity.ServicedRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicedRouteRepository extends JpaRepository<ServicedRoute, String> {
}
