package github.wozniak.flighttrackingservice.repository;

import github.wozniak.flighttrackingservice.entity.ScheduledRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledRouteRepository extends JpaRepository<ScheduledRoute, String> {
}
