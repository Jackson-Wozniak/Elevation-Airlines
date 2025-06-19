package github.wozniak.flighttrackingservice.airline_management.flight_manager.planning_algorithm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot generate path")
public class PathGenerationException extends RuntimeException{
    public PathGenerationException(){
        super();
    }
}
