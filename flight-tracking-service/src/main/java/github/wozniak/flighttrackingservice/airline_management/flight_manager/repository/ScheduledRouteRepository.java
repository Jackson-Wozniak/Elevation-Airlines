package github.wozniak.flighttrackingservice.airline_management.flight_manager.repository;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.ScheduledRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledRouteRepository extends JpaRepository<ScheduledRoute, String> {
}
