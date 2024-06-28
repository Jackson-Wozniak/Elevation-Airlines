package github.wozniak.flighttrackingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RouteGeneratorException extends RuntimeException{

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public RouteGeneratorException(String message){
        super(message);
    }
}
