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
}
