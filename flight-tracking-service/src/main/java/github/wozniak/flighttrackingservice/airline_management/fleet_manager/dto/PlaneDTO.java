package github.wozniak.flighttrackingservice.airline_management.fleet_manager.dto;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.Plane;
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
    private int speedKnots;
    private int rangeMiles;

    public PlaneDTO(Plane plane){
        this.callSign = plane.getCallSign();
        this.model = plane.getAircraft().getName();
        this.seatCapacity = plane.getAircraft().getSeatingCapacity();
        this.speedKnots = plane.getAircraft().getCruisingSpeedKnots();
        this.rangeMiles = plane.getAircraft().getRangeMiles();
    }
}
