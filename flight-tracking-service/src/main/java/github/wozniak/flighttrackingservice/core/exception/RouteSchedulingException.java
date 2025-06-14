package github.wozniak.flighttrackingservice.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RouteSchedulingException extends RuntimeException{

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public RouteSchedulingException(String message){
        super(message);
    }
}
