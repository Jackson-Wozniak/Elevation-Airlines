package github.wozniak.flighttrackingservice.airline_management.fleet_manager.service;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.repository.PlaneRepository;
import github.wozniak.flighttrackingservice.core.repository.PlaneModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//houses more management-style operations for the companies planes
@Service
@AllArgsConstructor
public class FleetService {
    private final PlaneRepository planeRepository;
    private final PlaneModelRepository planeModelRepository;
}
