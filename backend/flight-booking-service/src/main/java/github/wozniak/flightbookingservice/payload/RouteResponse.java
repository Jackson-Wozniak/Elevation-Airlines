package github.wozniak.flightbookingservice.payload;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class RouteResponse {

    private AirportResponse departure;
    private AirportResponse destination;
    private double flightHours;
    private double flightMiles;
}
