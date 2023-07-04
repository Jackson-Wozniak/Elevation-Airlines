package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.properties.PlaneList;
import github.wozniak.flighttrackingservice.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final FlightService flightService;

    public List<Plane> findAllPlanes(){
        return planeRepository.findAll();
    }

    @Modifying
    @Transactional
    public void deleteAllPlanesAndFlights(){
        planeRepository.deleteAll();
        flightService.deleteAllFlights();
    }

    public void saveDefaultPlanes(){
        planeRepository.saveAll(PlaneList.getDefaultPlanes());
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
