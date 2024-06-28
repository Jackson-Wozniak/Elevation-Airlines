package github.wozniak.flighttrackingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FlightQueryException extends RuntimeException{

    private final HttpStatus status = HttpStatus.NO_CONTENT;

    public FlightQueryException(String message){
        super(message);
    }
}
