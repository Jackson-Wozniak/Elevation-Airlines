package github.wozniak.flighttrackingservice.airline_management.flight_manager.planning_algorithm;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        for (Airport airport : airportNames) {
            try {
                List<Flight> filteredFlights = new ArrayList<>();
                for (Flight flight : flights) {
                    //if dest and dep match than that flight is the shortest path
                    if (flight.getRoute().getDepartureAirport().getIcaoCode().equals(departure) &&
                            flight.getRoute().getDestinationAirport().getIcaoCode().equals(destination)) {
                        return List.of(new Edge(new AirportNode(airport, List.of(flight)), flight));
                    }

                    if (flight.isMatchingAirport(airport.getIcaoCode(), true)) {
                        filteredFlights.add(flight);
                    }
                }
                AirportNode location = new AirportNode(airport, filteredFlights);
                nodes.add(location);
            } catch (Exception ignored) {}
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
