package github.wozniak.flighttrackingservice.economics.dtos;

import github.wozniak.flighttrackingservice.core.utils.NumberFormat;
import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.County;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityDTO {
    private String name;
    private String state;
    private String county;
    private long population;
    private String ranking;
    private int connectedMarkets;
    private long q1Passengers;
    private long q2Passengers;
    private long q3Passengers;
    private long q4Passengers;
    private String passengersCAGR;

    public CityDTO(City city){
        this.name = city.getName();
        this.state = city.getState();
        this.county = city.getCounty().getName();
        this.population = city.getEconomics().getPopulation();
        this.ranking = city.getEconomics().getRanking();
        this.q1Passengers = city.getEconomics().getAveragePassengersQ1();
        this.q2Passengers = city.getEconomics().getAveragePassengersQ2();
        this.q3Passengers = city.getEconomics().getAveragePassengersQ3();
        this.q4Passengers = city.getEconomics().getAveragePassengersQ4();
        this.passengersCAGR = NumberFormat.percent(city.getEconomics().getPassengersCAGR());
    }

    public static List<CityDTO> fromList(List<City> cities){
        return cities.stream().map(CityDTO::new).toList();
    }
}
