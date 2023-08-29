package github.wozniak.flightbookingservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : Jackson Wozniak
 * @created : 8/29/2023, Tuesday
 **/
/*
Used to store response for client calls to flight tracking service
 */
@Getter
@Setter
@AllArgsConstructor
public class FlightResponse {

    private long flightIdentifier;
    private PlaneResponse planeResponse;
    private RouteResponse routeResponse;
    private LocalDateTime takeoffTime;
}
