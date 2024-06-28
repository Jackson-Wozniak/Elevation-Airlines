package github.wozniak.flighttrackingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Dates must be formatted as mm/dd/yyyy")
public class DateFormatException extends RuntimeException{
}
