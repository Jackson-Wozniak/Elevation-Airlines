package github.wozniak.flighttrackingservice.economics.service;

import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.County;
import github.wozniak.flighttrackingservice.economics.repository.CityRepository;
import github.wozniak.flighttrackingservice.economics.repository.CountyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountyService countyService;

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public List<City> findAllCitiesAndCounties(){
        Map<String, County> counties = countyService.findAllCounties().stream()
                .collect(Collectors.toMap(County::getId, c -> c));
        List<City> cities = cityRepository.findAll();
        cities.forEach(city -> city.setCounty(counties.getOrDefault(city.getCounty().getId(), counties.get("UNKNOWN, UNKNOWN"))));
        return cities;
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
