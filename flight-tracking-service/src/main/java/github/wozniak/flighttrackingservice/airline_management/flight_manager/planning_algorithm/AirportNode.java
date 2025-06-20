package github.wozniak.flighttrackingservice.airline_management.flight_manager.planning_algorithm;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AirportNode {
    final private String icao;
    final private Airport airport;
    private final List<Flight> departingFlights;
    //these are edges between airports
    private final List<AirportNode> connectedAirports = new ArrayList<>();


    public AirportNode(Airport airport, List<Flight> connectingFlights) {
        this.icao = airport.getIcaoCode();
        this.airport = airport;
        this.departingFlights = connectingFlights;
    }

    //filter all known airport nodes to see which connect to this
    // (based on destination of connectingFlights
    public void addConnectedAirports(List<AirportNode> allAirports) {
        Set<String> destinations = this.departingFlights.stream()
                .map(flight -> flight.getRoute().getDestinationAirport().getIcaoCode())
                .filter(icaoCode -> !icaoCode.equals(this.icao))
                .collect(Collectors.toSet());
        for(AirportNode airport : allAirports){
            if(destinations.contains(airport.icao)) this.connectedAirports.add(airport);
        }
    }

    @Override
    public String toString() {
        return icao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirportNode that = (AirportNode) o;

        return Objects.equals(icao, that.icao);
    }

    @Override
    public int hashCode() {
        return icao != null ? icao.hashCode() : 0;
    }
}
