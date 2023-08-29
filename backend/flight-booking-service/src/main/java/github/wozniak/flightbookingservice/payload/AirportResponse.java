package github.wozniak.flightbookingservice.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 8/29/2023, Tuesday
 **/
@Getter
@Setter
@AllArgsConstructor
public class AirportResponse {

    private String icaoCode;
    private String name;
    private Double latitude;
    private Double longitude;
    private String continent;
    private String country;
}
