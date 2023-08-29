package github.wozniak.flightbookingservice.payload;

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
public class PlaneResponse {

    private String callSign;
    private String model;
    private int seatingCapacity;
    private int cruisingSpeedKnots;
    private int rangeMiles;
}
