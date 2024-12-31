package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.dto.TimeTableSummaryDTO;
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
            @RequestParam(value = "departure", required = false) String departure,
            @RequestParam(value = "destination", required = false) String dest
    ){
        if(departure == null && dest == null){
            return ResponseEntity.ok(flightInfoDTOs(flightService.findAllFlights()));
        }
        if(departure == null){
            return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightsByAirport(dest, false)));
        }
        if(dest == null){
            return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightsByAirport(departure, true)));
        }
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlights(departure, dest)));
    }

    @GetMapping(value = "/identifier/{identifier}")
    public ResponseEntity<FlightDTO> getFlightsByIdentifier(@PathVariable(value = "identifier") long identifier){
        return ResponseEntity.ok(new FlightDTO(flightService.findFlightsByIdentifier(identifier)));
    }

    @GetMapping(value = "/live")
    public ResponseEntity<List<FlightDTO>> getLiveFlights(){
        return ResponseEntity.ok(flightDTOs(flightService.findLiveFlights()));
    }

    @GetMapping(value = "/call_sign/{callSign}")
    public ResponseEntity<List<FlightDTO>> getFlightsByCallSign(@PathVariable("callSign") String callSign){
        return ResponseEntity.ok(flightDTOs(flightService.findFlightByCallSign(callSign, planeService)));
    }

    @GetMapping(value = "/time_table")
    public ResponseEntity<List<FlightSummaryDTO>> getFlightsByDate(@RequestParam("date") String date){
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightsByDate(date)));
    }

    @GetMapping(value = "/time_table", params = {"start", "end"})
    public ResponseEntity<List<TimeTableSummaryDTO>> returnFlightsOnDate(
            @RequestParam("start") String start, @RequestParam("end") String end){
        return ResponseEntity.ok(timeTableSummaryDTOS(flightService.findFlightsByDateRange(start, end)));
    }
}
