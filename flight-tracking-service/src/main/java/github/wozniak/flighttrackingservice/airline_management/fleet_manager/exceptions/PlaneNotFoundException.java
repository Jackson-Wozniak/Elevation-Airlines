package github.wozniak.flighttrackingservice.airline_management.fleet_manager.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PlaneNotFoundException extends RuntimeException{

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public PlaneNotFoundException(String message){
        super(message);
    }
}
