package github.wozniak.flighttrackingservice.economics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "county")
@Table(name = "counties")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class County {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "gdp")
    private Long gdpUSD;

    @Column(name = "gdp_cagr")
    private Double gdpCAGR;

    public County(String name, String state, long gdp, double CAGR){
        this.id = name + ", " + state;
        this.name = name;
        this.state = state;
        this.gdpUSD = gdp;
        this.gdpCAGR = CAGR;
    }
}
