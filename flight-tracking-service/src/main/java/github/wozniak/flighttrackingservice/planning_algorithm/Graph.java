package github.wozniak.flighttrackingservice.planning_algorithm;

import github.wozniak.flighttrackingservice.entity.Flight;

import java.util.List;

public class Graph {
    private final List<AirportNode> airportNodes;

    public Graph(List<AirportNode> airportNodes) {
        this.airportNodes = airportNodes;
    }

    public List<AirportNode> getVertexes() {
        return airportNodes;
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        for(AirportNode node : this.airportNodes){
            string.append(node.getIcao());
            for(Flight flight : node.getDepartingFlights()){
                string.append("\n     ").append(flight.toString());
            }
            string.append("\n");
        }
        return string.toString();
    }
}
