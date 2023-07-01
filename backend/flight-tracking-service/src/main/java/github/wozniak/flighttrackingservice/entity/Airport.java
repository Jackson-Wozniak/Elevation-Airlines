package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "airport")
@Table(name = "airports")
@Getter
@Setter
public class Airport {

    @Id
    private String icaoCode;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "continent")
    private String continent;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
