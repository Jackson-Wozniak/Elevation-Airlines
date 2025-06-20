package github.wozniak.flighttrackingservice.core.controller;

import github.wozniak.flighttrackingservice.core.dto.AirportDTO;
import github.wozniak.flighttrackingservice.core.dto.PlaneModelDTO;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.core.service.AircraftService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@AllArgsConstructor
public class AdminController {

    private final AircraftService aircraftService;
    private final AirportService airportService;

    @GetMapping(value = "/models")
    public ResponseEntity<List<PlaneModelDTO>> getPlaneModels(){
        return ResponseEntity.ok(PlaneModelDTO.fromList(aircraftService.findAllModels()));
    }
    
    @GetMapping(value = "/airports")
    public ResponseEntity<List<AirportDTO>> getAirports(){
        return ResponseEntity.ok(AirportDTO.fromList(airportService.findAllAirports()));
    }
}
