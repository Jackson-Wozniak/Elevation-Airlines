package github.wozniak.flighttrackingservice.airline_management.flight_manager.planning_algorithm;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;

import java.util.*;

public class DijkstraAlgorithm {

    private final List<AirportNode> nodes;
    private Set<AirportNode> settledNodes;
    private Set<AirportNode> unSettledNodes;
    private Map<AirportNode, Edge> predecessors;
    private Map<AirportNode, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<>(graph.getVertexes());
    }

    public void execute(AirportNode source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            AirportNode node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(AirportNode node) {
        List<AirportNode> adjacentNodes = node.getConnectedAirports();
        for (AirportNode target : adjacentNodes) {
            List<Flight> chosenRoutes = getRoutes(node, target);
            for(int i = 0; i < chosenRoutes.size(); i++){
                Flight route = chosenRoutes.get(i);
                if (getShortestDistance(target) > getShortestDistance(node)
                        + route.getRoute().getFlightDistanceMiles()) {
//                    if(predecessors.get(node) != null){
//                        if(predecessors.get(node).getFlight().getLandingDateTime().isAfter(route.getTakeOffDateTime())){
//                            if(i == chosenRoutes.size() - 1){
//                                throw new RuntimeException();
//                            }
//                            continue;
//                        }
//                    }
                    distance.put(target, (int) Math.round(getShortestDistance(node)
                            + route.getRoute().getFlightDistanceMiles()));
                    predecessors.put(target, new Edge(node, route));
                    unSettledNodes.add(target);
                    break;
                }
            }
        }

    }

    private List<Flight> getRoutes(AirportNode node, AirportNode target) {
        return node.getDepartingFlights().stream()
                .filter(dest -> dest.getRoute().getDestinationAirport().getIcaoCode().equals(target.getAirport().getIcaoCode()))
                .sorted(Comparator.comparing(Flight::getTakeOffDateTime)).toList();
    }

    private AirportNode getMinimum(Set<AirportNode> airportNodes) {
        AirportNode minimum = null;
        for (AirportNode airportNode : airportNodes) {
            if (minimum == null) {
                minimum = airportNode;
            } else {
                if (getShortestDistance(airportNode) < getShortestDistance(minimum)) {
                    minimum = airportNode;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(AirportNode airportNode) {
        return settledNodes.contains(airportNode);
    }

    private int getShortestDistance(AirportNode destination) {
        Integer d = distance.get(destination);
        return Objects.requireNonNullElse(d, Integer.MAX_VALUE);
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Edge> getPath(AirportNode target) {
        LinkedList<Edge> path = new LinkedList<>();
        AirportNode step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        while (predecessors.get(step) != null) {
            Edge edge = predecessors.get(step);
            step = edge.getSource();
            Edge newEdge = new Edge(step, edge.getFlight());
            if(path.contains(newEdge)) continue;
            path.add(newEdge);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
