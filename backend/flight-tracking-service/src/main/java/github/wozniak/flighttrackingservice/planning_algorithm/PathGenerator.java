package github.wozniak.flighttrackingservice.planning_algorithm;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.properties.AirportsList;
import github.wozniak.flighttrackingservice.service.AirportService;
import github.wozniak.flighttrackingservice.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PathGenerator {

    private final FlightService flightService;
    private final AirportService airportService;

    public List<Edge> uniquePath(String departure, String destination) {
        return generate(flightService.findAllFlights(), departure, destination);
    }

    //method overload allows for custom flights in testing
    public List<Edge> uniquePath(List<Flight> flights, String departure, String destination) {
        return generate(flights, departure, destination);
    }

    private List<Edge> generate(List<Flight> flights, String departure, String destination){
        List<Airport> airportNames = airportService.findAllAirports();
        List<AirportNode> nodes = new ArrayList<>();
        for (int i = 0; i < airportNames.size(); i++) {
            String icao = airportNames.get(i).getIcaoCode();
            try{
                List<Flight> filteredFlights = flights.stream().filter(flight ->
                        flight.isMatchingAirport(icao, true)).toList();
                AirportNode location = new AirportNode(airportNames.get(i), filteredFlights);
                nodes.add(location);
            }catch (Exception ignored){}
        }
        for(AirportNode airport : nodes){
            airport.addConnectedAirports(nodes);
        }

        Graph graph = new Graph(nodes);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        dijkstra.execute(nodes.stream()
                .filter(node -> node.getIcao().equals(departure))
                .findFirst().orElseThrow(PathGenerationException::new));
        AirportNode destinationNode = nodes.stream()
                .filter(node -> node.getIcao().equals(destination))
                .findFirst().orElseThrow(PathGenerationException::new);

        List<Edge> path = dijkstra.getPath(destinationNode);
        if (path == null) throw new NoFlightPathException();

        return path;
    }
}
