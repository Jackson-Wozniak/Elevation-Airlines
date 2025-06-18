package github.wozniak.flighttrackingservice.core.service;

import github.wozniak.flighttrackingservice.core.entity.Plane;
import github.wozniak.flighttrackingservice.flight_management.service.FlightService;
import github.wozniak.flighttrackingservice.core.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    public long planeCount(){
        return planeRepository.count();
    }

    public List<Plane> findUnusedPlanes(List<Plane> usedPlanes){
        List<Plane> planes = planeRepository.findAll();
        planes.removeAll(usedPlanes);
        return planes;
    }
}
