package github.wozniak.flighttrackingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(String message){
        super(message);
    }
}
