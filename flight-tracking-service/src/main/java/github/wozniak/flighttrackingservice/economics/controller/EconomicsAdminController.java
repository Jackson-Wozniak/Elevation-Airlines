package github.wozniak.flighttrackingservice.economics.controller;

import github.wozniak.flighttrackingservice.economics.dtos.CityDTO;
import github.wozniak.flighttrackingservice.economics.dtos.CountyDTO;
import github.wozniak.flighttrackingservice.economics.service.CityService;
import github.wozniak.flighttrackingservice.economics.service.CountyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/economics/admin")
@AllArgsConstructor
public class EconomicsAdminController {
    private final CityService cityService;
    private final CountyService countyService;

    @GetMapping(value = "/cities")
    public ResponseEntity<List<CityDTO>> getCities(){
        return ResponseEntity.ok(CityDTO.fromList(cityService.findAllCities()));
    }

    @GetMapping(value = "/counties")
    public ResponseEntity<List<CountyDTO>> getCounties(){
        return ResponseEntity.ok(CountyDTO.fromList(countyService.findAllCounties()));
    }
}
