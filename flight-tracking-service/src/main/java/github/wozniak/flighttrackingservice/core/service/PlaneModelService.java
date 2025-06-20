package github.wozniak.flighttrackingservice.core.service;

import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import github.wozniak.flighttrackingservice.core.repository.PlaneModelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaneModelService {
    private PlaneModelRepository planeModelRepository;

    @Modifying
    @Transactional
    public void deleteAllModels(){
        planeModelRepository.deleteAll();
    }

    public void saveDefaultModels(List<Aircraft> models){
        planeModelRepository.saveAll(models);
    }

    public List<Aircraft> findAllModels(){
        return planeModelRepository.findAll();
    }
}
