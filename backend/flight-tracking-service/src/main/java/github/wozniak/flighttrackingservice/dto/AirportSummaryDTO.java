package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Airport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportSummaryDTO {

    private String icaoCode;
    private String name;

    public AirportSummaryDTO(Airport airport){
        this.icaoCode = airport.getIcaoCode();
        this.name = airport.getName();
    }
}
