package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plane {

    private String callSign;
    private String model;
    private Integer economySeatingCapacity;
    private Integer luxurySeatingCapacity;
    private Double economySeatingPrice;
    private Double luxurySeatingPrice;
}
