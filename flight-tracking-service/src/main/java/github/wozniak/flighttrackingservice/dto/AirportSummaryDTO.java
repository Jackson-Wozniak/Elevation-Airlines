package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AirportSummaryDTO {

    private String icaoCode;
    private String name;
    private String country;

    public AirportSummaryDTO(Airport airport){
        this.icaoCode = airport.getIcaoCode();
        this.name = airport.getName();
        this.country = airport.getCountry();
    }
}
