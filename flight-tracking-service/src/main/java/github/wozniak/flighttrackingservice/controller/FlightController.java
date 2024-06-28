package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.dto.TimeTableSummaryDTO;
import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.planning_algorithm.Edge;
import github.wozniak.flighttrackingservice.planning_algorithm.PathGenerator;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final PlaneService planeService;
    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<?> getFlights(){
        return ResponseEntity.ok(flightService.findAllFlights().stream().map(FlightSummaryDTO::new).toList());
    }

    @GetMapping(params = "departure")
    public ResponseEntity<?> getFlightsByDeparture(
            @RequestParam(value = "departure", defaultValue = "") String departure){
        return ResponseEntity.ok(flightService.findFlightsByAirport(
                departure, true, airportService)
                .stream().map(FlightSummaryDTO::new).toList());
    }

    @GetMapping(params = "destination")
    public ResponseEntity<?> getFlightsByDestination(
            @RequestParam(value = "destination", defaultValue = "") String destination){
        return ResponseEntity.ok(flightService.findFlightsByAirport(
                        destination, true, airportService)
                .stream().map(FlightSummaryDTO::new).toList());
    }

    @GetMapping(value = "/identifier/{identifier}")
    public ResponseEntity<?> getFlightsByIdentifier(@PathVariable(value = "identifier") long identifier){
        return ResponseEntity.ok(new FlightDTO(flightService.findFlightsByIdentifier(identifier)));
    }

    @GetMapping(value = "/live")
    public ResponseEntity<?> getLiveFlights(){
        return ResponseEntity.ok(flightService.findLiveFlights().stream()
                .map(FlightDTO::new).toList());
    }

    @GetMapping(value = "/call_sign/{callSign}")
    public ResponseEntity<?> getFlightsByCallSign(@PathVariable("callSign") String callSign){
        return ResponseEntity.ok(flightService.findFlightByCallSign(callSign, planeService).stream()
                .map(FlightDTO::new).toList());
    }

    @GetMapping(value = "/time_table")
    public ResponseEntity<?> getFlightsByDate(@RequestParam("date") String date){
        return ResponseEntity.ok().body(flightService.findFlightsByDate(date).stream()
                .map(FlightSummaryDTO::new).toList());
    }

    @GetMapping(value = "/time_table", params = {"start", "end"})
    public ResponseEntity<?> returnFlightsOnDate(
            @RequestParam("start") String start, @RequestParam("end") String end){
        return ResponseEntity.ok().body(flightService.findFlightsByDateRange(start, end).stream()
                        .map(TimeTableSummaryDTO::new).toList());
    }
}
