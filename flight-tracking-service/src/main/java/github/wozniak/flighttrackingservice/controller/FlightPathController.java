package github.wozniak.flighttrackingservice.controller;

import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.planning_algorithm.Edge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class FlightPathController {

    /*
    TODO:
     add URL param optimizer to find SPF based on total time past,
     least # of connecting flights, or lowest distance traveled

     default optimizer is quickest flight time (takeoff at departure to landing at destination)
    */
//    @RequestMapping(value = "path_generator")
//    public List<FlightSummaryDTO> generateConnectingFlightPath(@RequestParam("departure")String departure, @RequestParam("destination") String destination){
//        Airport departureAirport = airportService.findAirportByICAO(departure);
//        Airport destinationAirport = airportService.findAirportByICAO(destination);
//        return pathGenerator.uniquePath(departure, destination).stream()
//                .map(Edge::getFlight).map(FlightSummaryDTO::new).toList();
//    }
}
