package github.wozniak.flighttrackingservice.flight_management.planning_algorithm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot generate path")
public class PathGenerationException extends RuntimeException{
    public PathGenerationException(){
        super();
    }
}
