package github.wozniak.flighttrackingservice.core.service;

import github.wozniak.flighttrackingservice.core.entity.Plane;
import github.wozniak.flighttrackingservice.core.model.PlaneModel;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.flight_management.service.FlightService;
import github.wozniak.flighttrackingservice.core.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final FlightService flightService;
    private static final Random random = new Random();

    public List<Plane> findAllPlanes(){
        return planeRepository.findAll();
    }

    public boolean planeExists(String callSign){
        return planeRepository.findById(callSign).isPresent();
    }

    @Modifying
    @Transactional
    public void deleteAllPlanes(){
        planeRepository.deleteAll();
    }

    public void generateAndSavePlanes(List<PlaneModel> models){
        List<Plane> fleet = new ArrayList<>();
        ArrayList<Integer> callSigns = new ArrayList<>(IntStream.range(100, 999).boxed().toList());
        for(int i = 0; i < ElevationAirlineProperties.FLEET_COUNT; i++){
            int callSign = callSigns.get(random.nextInt(callSigns.size()));
            callSigns.remove(Integer.valueOf(callSign));
            fleet.add(new Plane("ELV" + callSign, PlaneModel.randomPlane(models, random)));
        }
        planeRepository.saveAll(fleet);
    }

    public long planeCount(){
        return planeRepository.count();
    }

    public List<Plane> findUnusedPlanes(List<Plane> usedPlanes){
        List<Plane> planes = planeRepository.findAll();
        planes.removeAll(usedPlanes);
        return planes;
    }
}
