package github.wozniak.flighttrackingservice.airline_management.fleet_manager.service;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.Plane;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.PlaneStatus;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.repository.PlaneRepository;
import github.wozniak.flighttrackingservice.airline_management.network_planner.service.NetworkService;
import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.repository.AircraftRepository;
import github.wozniak.flighttrackingservice.core.service.AircraftService;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//houses more management-style operations for the companies planes
@Service
@AllArgsConstructor
public class FleetService {
    private final PlaneService planeService;
    private final AircraftService aircraftService;
    private final NetworkService networkService;
    private static final Random random = new Random();

    public int generateStartingFleet(){
        Airport hub = networkService.findPrimaryHub();
        LocalDateTime now = LocalDateTime.now();
        List<Aircraft> aircrafts = aircraftService.findAllAircraft();
        Map<Integer, Plane> fleet = new HashMap<>();
        int[] callsignRange = ElevationAirlineProperties.CALLSIGN_RANGE;

        for(int i = 0; i < ElevationAirlineProperties.FLEET_COUNT; i++){
            int id = random.nextInt((callsignRange[1] - callsignRange[0]) + 1) + callsignRange[0];
            if(fleet.containsKey(id)){
                i--;
                continue;
            }
            fleet.put(id, new Plane(id, aircrafts.get(random.nextInt(aircrafts.size())), new PlaneStatus(hub, now)));
        }
        return planeService.savePlanes(fleet.values().stream().toList());
    }
}
