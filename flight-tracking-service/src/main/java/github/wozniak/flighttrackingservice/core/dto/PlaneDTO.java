package github.wozniak.flighttrackingservice.core.dto;

import github.wozniak.flighttrackingservice.core.entity.Plane;
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

    public PlaneDTO(Plane plane){
        this.callSign = plane.getCallSign();
        this.model = plane.getModel();
        this.seatCapacity = plane.getSeatingCapacity();
        this.luxurySeats = plane.getLuxurySeats();
        this.speedKnots = plane.getCruisingSpeedKnots();
        this.rangeMiles = plane.getRangeMiles();
    }
}
