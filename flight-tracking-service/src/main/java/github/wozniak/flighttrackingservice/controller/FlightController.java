package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static github.wozniak.flighttrackingservice.utils.DTOListMapper.*;

@RestController
@RequestMapping(value = "/api/v1/flights")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class FlightController {

    private final FlightService flightService;
    private final PlaneService planeService;

    @GetMapping
    public ResponseEntity<List<?>> getFlights(
            @RequestParam(value = "departure", required = false) String dep,
            @RequestParam(value = "destination", required = false) String dest,
            @RequestParam(value = "is_detailed", required = false) String isDetailed
    ){
        List<Flight> flights;
        if(dep == null && dest == null){
            flights = flightService.findAllFlights();
        }else if(dep == null){
            flights = flightService.findFlightsByAirport(dest, false);
        }else if(dest == null){
            flights = flightService.findFlightsByAirport(dep, true);
        }else flights = flightService.findFlights(dep, dest);

        if(isDetailed.equalsIgnoreCase("true") || isDetailed.equalsIgnoreCase("t")){
            return ResponseEntity.ok(flightDTOs(flights));
        }
        return ResponseEntity.ok(flightSummaryDTOs(flights));
    }

    @GetMapping(value = "/identifier/{identifier}")
    public ResponseEntity<?> getFlightsByIdentifier(
            @PathVariable(value = "identifier") long identifier,
            @RequestParam(value = "is_detailed", required = false) String isDetailed
    ){
        if(isDetailed.equalsIgnoreCase("true") || isDetailed.equalsIgnoreCase("t")){
            return ResponseEntity.ok(new FlightDTO(flightService.findFlightsByIdentifier(identifier)));
        }
        return ResponseEntity.ok(new FlightSummaryDTO(flightService.findFlightsByIdentifier(identifier)));
    }

    @GetMapping(value = "/live")
    public ResponseEntity<List<?>> getLiveFlights(
            @RequestParam(value = "is_detailed", required = false) String isDetailed
    ){
        if(isDetailed.equalsIgnoreCase("true") || isDetailed.equalsIgnoreCase("i")){
            return ResponseEntity.ok(flightDTOs(flightService.findLiveFlights()));
        }
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findLiveFlights()));
    }

    @GetMapping(value = "/call_sign/{callSign}")
    public ResponseEntity<List<?>> getFlightsByCallSign(
            @PathVariable("callSign") String callSign,
            @RequestParam(value = "is_detailed", required = false) String isDetailed
    ){
        if(isDetailed.equalsIgnoreCase("true") || isDetailed.equalsIgnoreCase("i")){
            return ResponseEntity.ok(flightDTOs(flightService.findFlightByCallSign(callSign, planeService)));
        }
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightByCallSign(callSign, planeService)));
    }

    @GetMapping(value = "/time_table")
    public ResponseEntity<List<?>> getFlightsByDate(
            @RequestParam("start") String start,
            @RequestParam("end") String end,
            @RequestParam(value = "is_detailed", required = false) String isDetailed
    ){
        if(isDetailed.equalsIgnoreCase("true") || isDetailed.equalsIgnoreCase("t")){
            return ResponseEntity.ok(timeTableDTOS(flightService.findFlightsByDateRange(start, end)));
        }
        return ResponseEntity.ok(timeTableSummaryDTOS(flightService.findFlightsByDateRange(start, end)));
    }
}
