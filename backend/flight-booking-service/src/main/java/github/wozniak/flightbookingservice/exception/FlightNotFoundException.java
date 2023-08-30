package github.wozniak.flightbookingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Jackson Wozniak
 * @created : 8/30/2023, Wednesday
 **/
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no flight found with given identifier")
public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(String message){
        super(message);
    }
}
