package github.wozniak.flighttrackingservice.economics.service;

import github.wozniak.flighttrackingservice.economics.entity.County;
import github.wozniak.flighttrackingservice.economics.repository.CountyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;

    public List<County> findAllCounties(){
        return countyRepository.findAll();
    }

    public List<County> saveDefaultCounties(List<County> counties){
        return countyRepository.saveAll(counties);
    }

    @Modifying
    @Transactional
    public void deleteAllCounties(){
        countyRepository.deleteAll();
    }
}
