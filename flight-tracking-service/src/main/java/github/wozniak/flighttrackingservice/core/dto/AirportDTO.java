package github.wozniak.flighttrackingservice.core.dto;

import github.wozniak.flighttrackingservice.core.entity.Airport;
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

    public AirportDTO(Airport airport){
        this.icaoCode = airport.getIcaoCode();
        this.name = airport.getName();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
        this.continent = airport.getContinent();
        this.country = airport.getCountry();
    }
}
