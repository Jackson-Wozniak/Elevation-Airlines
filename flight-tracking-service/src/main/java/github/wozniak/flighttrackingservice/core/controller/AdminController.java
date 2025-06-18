package github.wozniak.flighttrackingservice.core.controller;

import github.wozniak.flighttrackingservice.core.dto.PlaneModelDTO;
import github.wozniak.flighttrackingservice.core.service.PlaneModelService;
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

    private final PlaneModelService planeModelService;

    @GetMapping(value = "/models")
    public ResponseEntity<List<PlaneModelDTO>> getPlaneModels(){
        return ResponseEntity.ok(PlaneModelDTO.fromList(planeModelService.findAllModels()));
    }
}
