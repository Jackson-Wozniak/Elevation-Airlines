package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.dto.FlightTimeTableDTO;
import github.wozniak.flighttrackingservice.dto.TimeTableSummaryDTO;
import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.planning_algorithm.Edge;
import github.wozniak.flighttrackingservice.planning_algorithm.FlightPath;
import github.wozniak.flighttrackingservice.planning_algorithm.FlightPathDTO;
import github.wozniak.flighttrackingservice.planning_algorithm.PathGenerator;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tracking")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final PlaneService planeService;
    private final AirportService airportService;
    private final PathGenerator pathGenerator;

    @GetMapping(value = "")
    public ResponseEntity<?> returnAllFlights(
            @RequestParam(value = "departure", defaultValue = "") String departure,
            @RequestParam(value = "destination", defaultValue = "") String destination){
        if(!departure.equals("")){
            return ResponseEntity.ok(flightService.findFlightsByAirport(
                    departure, true, airportService)
                        .stream().map(FlightSummaryDTO::new).toList());
        }
        if(!destination.equals("")){
            return ResponseEntity.ok(flightService.findFlightsByAirport(
                    destination, false, airportService)
                        .stream().map(FlightSummaryDTO::new).toList());
        }
        return ResponseEntity.ok(flightService.findAllFlights().stream().map(FlightSummaryDTO::new).toList());
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
                .map(FlightSummaryDTO::new).toList());
    }

    @GetMapping(value = "/time_table/range")
    public ResponseEntity<?> returnFlightsOnDate(@RequestParam("start") String start, @RequestParam("end") String end){
        return ResponseEntity.ok().body(flightService.findFlightsByDateRange(start, end).stream()
                        .map(TimeTableSummaryDTO::new).toList());
    }

    /*
    TODO:
     add URL param optimizer to find SPF based on total time past,
     least # of connecting flights, or lowest distance traveled

     default optimizer is quickest flight time (takeoff at departure to landing at destination)
    */
    @RequestMapping(value = "path_generator")
    public List<Edge> generateConnectingFlightPath(@RequestParam("departure")String departure, @RequestParam("destination") String destination){
        Airport departureAirport = airportService.findAirportByICAO(departure);
        Airport destinationAirport = airportService.findAirportByICAO(destination);
        return pathGenerator.uniquePath(departure, destination);
    }
}
