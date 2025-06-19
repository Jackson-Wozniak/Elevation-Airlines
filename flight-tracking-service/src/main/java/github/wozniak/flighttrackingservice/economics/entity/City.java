package github.wozniak.flighttrackingservice.economics.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "city")
@Table(name = "cities")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "county", nullable = false)
    private County county;

    @Embedded
    private CityStats economics;

    public City(String name, String state, County county, CityStats stats){
        this.id = name + ", " + state;
        this.name = name;
        this.state = state;
        this.county = county;
        this.economics = stats;
    }
}
