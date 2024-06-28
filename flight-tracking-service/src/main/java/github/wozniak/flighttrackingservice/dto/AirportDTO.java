package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AirportDTO {

    private String icaoCode;
    private String name;
    private double latitude;
    private double longitude;
    private String continent;
    private String country;
}
