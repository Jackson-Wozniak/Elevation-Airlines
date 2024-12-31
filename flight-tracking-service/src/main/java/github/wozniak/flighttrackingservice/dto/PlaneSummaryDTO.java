package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Plane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlaneSummaryDTO {

    private String callSign;
    private String model;

    public PlaneSummaryDTO(Plane plane){
        this.callSign = plane.getCallSign();
        this.model = plane.getModel();
    }
}
