package github.wozniak.flighttrackingservice.economics.service;

import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public void saveDefaultCities(List<City> cities){
        cityRepository.saveAll(cities);
    }

    @Modifying
    @Transactional
    public void deleteAllCities(){
        cityRepository.deleteAll();
    }
}
