package github.wozniak.flighttrackingservice.flight_management.repository;

import github.wozniak.flighttrackingservice.flight_management.entity.ScheduledRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledRouteRepository extends JpaRepository<ScheduledRoute, String> {
}
