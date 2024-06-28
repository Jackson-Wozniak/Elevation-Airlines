package github.wozniak.flighttrackingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class PlaneNotFoundException extends RuntimeException{

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public PlaneNotFoundException(String message){
        super(message);
    }
}
