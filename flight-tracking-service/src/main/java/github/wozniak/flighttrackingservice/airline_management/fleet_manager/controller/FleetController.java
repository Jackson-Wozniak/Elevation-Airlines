package github.wozniak.flighttrackingservice.airline_management.fleet_manager.controller;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.FleetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fleet")
@AllArgsConstructor
public class FleetController {
    private final FleetService fleetService;
}
