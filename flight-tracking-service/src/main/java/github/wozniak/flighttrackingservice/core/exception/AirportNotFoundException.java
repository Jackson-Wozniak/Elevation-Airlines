package github.wozniak.flighttrackingservice.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AirportNotFoundException extends RuntimeException{

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public AirportNotFoundException(String message){
        super(message);
    }
}
