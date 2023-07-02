package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.properties.PlaneList;
import github.wozniak.flighttrackingservice.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaneService {

    @Autowired
    private final PlaneRepository planeRepository;
    @Autowired
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
        planeRepository.truncatePlanes();
        planeRepository.saveAll(PlaneList.getDefaultPlanes());
    }

    public long planeCount(){
        return planeRepository.count();
    }
}
