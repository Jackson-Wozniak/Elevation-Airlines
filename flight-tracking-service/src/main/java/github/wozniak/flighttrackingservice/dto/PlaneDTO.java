package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Plane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlaneDTO {

    private String callSign;
    private String model;
    private int seatCapacity;
    private int luxurySeats;
    private int speedKnots;
    private int rangeMiles;
}
