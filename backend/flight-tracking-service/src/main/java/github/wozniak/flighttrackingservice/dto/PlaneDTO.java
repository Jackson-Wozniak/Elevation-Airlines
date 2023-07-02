package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Plane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaneDTO {

    private String callSign;
    private String model;
    private int seatingCapacity;
    private int luxurySeats;
    private double baseSeatingPrice;
    private int speedKnots;
    private int rangeMiles;

    public PlaneDTO(Plane plane){
        this.callSign = plane.getCallSign();
        this.model = plane.getModel();
        this.seatingCapacity = plane.getSeatingCapacity();
        this.luxurySeats = plane.getLuxurySeats();
        this.speedKnots = plane.getCruisingSpeedKnots();
        this.rangeMiles = plane.getRangeMiles();
    }
}
