package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightTimeTableDTO;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/tracking")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final PlaneService planeService;
    private final AirportService airportService;

    @GetMapping(value = "")
    public ResponseEntity<?> returnAllFlights(
            @RequestParam(value = "departure", defaultValue = "") String departure,
            @RequestParam(value = "destination", defaultValue = "") String destination){
        if(!departure.equals("")){
            return ResponseEntity.ok(flightService.findFlightsByAirport(
                    departure, true, airportService)
                        .stream().map(FlightDTO::new).toList());
        }
        if(!destination.equals("")){
            return ResponseEntity.ok(flightService.findFlightsByAirport(
                    destination, false, airportService)
                        .stream().map(FlightDTO::new).toList());
        }
        return ResponseEntity.ok(flightService.findAllFlights().stream().map(FlightDTO::new).toList());
    }

    @GetMapping(value = "/identifier/{identifier}")
    public ResponseEntity<?> returnAllFlights(@PathVariable(value = "identifier") long identifier){
        return ResponseEntity.ok(new FlightDTO(flightService.findFlightsByIdentifier(identifier)));
    }

    @GetMapping(value = "/live_flights")
    public ResponseEntity<?> returnAllLiveFlights(){
        return ResponseEntity.ok(flightService.findLiveFlights().stream()
                .map(FlightDTO::new).toList());
    }

    /*
    200 code for flights found
    204 code for plane exists but no flights scheduled
    404 for plane that doesn't exist
     */
    @GetMapping(value = "/call_sign/{callSign}")
    public ResponseEntity<?> returnFlightByCallSign(@PathVariable("callSign") String callSign){
        return ResponseEntity.ok(flightService.findFlightByCallSign(callSign, planeService).stream()
                .map(FlightDTO::new).toList());
    }

    @GetMapping(value = "/time_table")
    public ResponseEntity<?> returnFlightsOnDate(@RequestParam("date") String date){
        return ResponseEntity.ok().body(flightService.findFlightsByDate(date).stream()
                .map(FlightDTO::new).toList());
    }

    @GetMapping(value = "/time_table/range")
    public ResponseEntity<?> returnFlightsOnDate(@RequestParam("start") String start, @RequestParam("end") String end){
        return ResponseEntity.ok().body(flightService.findFlightsByDateRange(start, end).stream()
                        .map(FlightTimeTableDTO::new).toList());
    }

    /*
    TODO:
        - create endpoint to generate a path between 2 airports based on scheduled flights, if possible
     */
}
