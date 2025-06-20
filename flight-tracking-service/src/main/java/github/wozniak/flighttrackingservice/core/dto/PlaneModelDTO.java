package github.wozniak.flighttrackingservice.core.dto;

import github.wozniak.flighttrackingservice.core.entity.Aircraft;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaneModelDTO {
    private String make;
    private String model;
    private String type;
    private Integer seatingCapacity;
    private Integer cruisingSpeedKnots;
    private Integer rangeMiles;

    public PlaneModelDTO(Aircraft model){
        this.make = model.getMake();
        this.model = model.getModel();
        this.type = model.getType().toString();
        this.seatingCapacity = model.getSeatingCapacity();
        this.cruisingSpeedKnots = model.getCruisingSpeedKnots();
        this.rangeMiles = model.getRangeMiles();
    }

    public static List<PlaneModelDTO> fromList(List<Aircraft> models){
        return models.stream().map(PlaneModelDTO::new).toList();
    }
}
