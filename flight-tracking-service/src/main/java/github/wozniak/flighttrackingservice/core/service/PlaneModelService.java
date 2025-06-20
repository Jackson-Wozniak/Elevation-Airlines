package github.wozniak.flighttrackingservice.core.service;

import github.wozniak.flighttrackingservice.core.entity.PlaneModel;
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

    public void saveDefaultModels(List<PlaneModel> models){
        planeModelRepository.saveAll(models);
    }

    public List<PlaneModel> findAllModels(){
        return planeModelRepository.findAll();
    }
}
