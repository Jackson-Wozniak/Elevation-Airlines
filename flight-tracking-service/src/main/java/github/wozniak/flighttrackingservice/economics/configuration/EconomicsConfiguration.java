package github.wozniak.flighttrackingservice.economics.configuration;

import github.wozniak.flighttrackingservice.core.data.CSVReader;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.County;
import github.wozniak.flighttrackingservice.economics.service.CityService;
import github.wozniak.flighttrackingservice.economics.service.CountyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class EconomicsConfiguration {
    private final CountyService countyService;
    private final CityService cityService;
    private final Logger logger = LoggerFactory.getLogger(EconomicsConfiguration.class);

    public void configure() throws IOException {
        if(ElevationAirlineProperties.DELETE_DATA_ON_STARTUP_MODE){
            ArrayList<County> defaultCounties = new ArrayList<>(CSVReader.counties());
            defaultCounties.add(new County("UNKNOWN", "UNKNOWN", 0, 0.0));

            logger.info("SAVING (" + defaultCounties.size() + ") COUNTIES");
            defaultCounties = new ArrayList<>(countyService.saveDefaultCounties(defaultCounties));

            Map<String, County> counties = new HashMap<>();
            defaultCounties.forEach(county -> counties.put(county.getId(), county));

            List<City> defaultCities = CSVReader.cities(counties);
            logger.info("SAVING (" + defaultCities.size() + ") CITIES");
            cityService.saveDefaultCities(defaultCities);
        }
    }
}
