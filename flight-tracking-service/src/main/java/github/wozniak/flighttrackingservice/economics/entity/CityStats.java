package github.wozniak.flighttrackingservice.economics.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CityStats {
    private Long population;
    private String ranking;
    private Integer connectedMarkets;
    private Long averagePassengersQ1;
    private Long averagePassengersQ2;
    private Long averagePassengersQ3;
    private Long averagePassengersQ4;
    private Double passengersCAGR;

    public CityStats(long population, String ranking, int connectedMarkets,
                     long averagePassengersQ1, long averagePassengersQ2,
                     long averagePassengersQ3, long averagePassengersQ4, double passengersCAGR) {
        this.population = population;
        this.ranking = ranking;
        this.connectedMarkets = connectedMarkets;
        this.averagePassengersQ1 = averagePassengersQ1;
        this.averagePassengersQ2 = averagePassengersQ2;
        this.averagePassengersQ3 = averagePassengersQ3;
        this.averagePassengersQ4 = averagePassengersQ4;
        this.passengersCAGR = passengersCAGR;
    }
}
