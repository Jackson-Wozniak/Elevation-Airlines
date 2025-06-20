package github.wozniak.flighttrackingservice.core.service;

import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import github.wozniak.flighttrackingservice.core.repository.AircraftRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AircraftService {
    private AircraftRepository aircraftRepository;

    @Modifying
    @Transactional
    public void deleteAllModels(){
        aircraftRepository.deleteAll();
    }

    public void saveDefaultModels(List<Aircraft> models){
        aircraftRepository.saveAll(models);
    }

    public List<Aircraft> findAllModels(){
        return aircraftRepository.findAll();
    }
}
