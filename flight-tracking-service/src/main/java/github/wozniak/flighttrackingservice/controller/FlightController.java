package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static github.wozniak.flighttrackingservice.utils.DTOListMapper.*;

@RestController
@RequestMapping(value = "/api/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final PlaneService planeService;
    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<?> getFlights(){
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findAllFlights()));
    }

    @GetMapping(params = "departure")
    public ResponseEntity<?> getFlightsByDeparture(
            @RequestParam(value = "departure", defaultValue = "") String departure){
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightsByAirport(
                departure, true, airportService)));
    }

    @GetMapping(params = "destination")
    public ResponseEntity<?> getFlightsByDestination(
            @RequestParam(value = "destination", defaultValue = "") String destination){
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightsByAirport(
                destination, false, airportService)));
    }

    @GetMapping(value = "/identifier/{identifier}")
    public ResponseEntity<?> getFlightsByIdentifier(@PathVariable(value = "identifier") long identifier){
        return ResponseEntity.ok(flightService.findFlightsByIdentifier(identifier).getDTO());
    }

    @GetMapping(value = "/live")
    public ResponseEntity<?> getLiveFlights(){
        return ResponseEntity.ok(flightDTOs(flightService.findLiveFlights()));
    }

    @GetMapping(value = "/call_sign/{callSign}")
    public ResponseEntity<?> getFlightsByCallSign(@PathVariable("callSign") String callSign){
        return ResponseEntity.ok(flightDTOs(flightService.findFlightByCallSign(callSign, planeService)));
    }

    @GetMapping(value = "/time_table")
    public ResponseEntity<?> getFlightsByDate(@RequestParam("date") String date){
        return ResponseEntity.ok(flightSummaryDTOs(flightService.findFlightsByDate(date)));
    }

    @GetMapping(value = "/time_table", params = {"start", "end"})
    public ResponseEntity<?> returnFlightsOnDate(
            @RequestParam("start") String start, @RequestParam("end") String end){
        return ResponseEntity.ok(timeTableSummaryDTOS(flightService.findFlightsByDateRange(start, end)));
    }
}
