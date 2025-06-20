package github.wozniak.flighttrackingservice.core.dto;

import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AircraftDTO {
    private String make;
    private String model;
    private String type;
    private Integer seatingCapacity;
    private Integer cruisingSpeedKnots;
    private Integer rangeMiles;

    public AircraftDTO(Aircraft model){
        this.make = model.getMake();
        this.model = model.getModel();
        this.type = model.getAircraftCategory().toString();
        this.seatingCapacity = model.getSeatingCapacity();
        this.cruisingSpeedKnots = model.getCruisingSpeedKnots();
        this.rangeMiles = model.getRangeMiles();
    }

    public static List<AircraftDTO> fromList(List<Aircraft> models){
        return models.stream().map(AircraftDTO::new).toList();
    }
}
