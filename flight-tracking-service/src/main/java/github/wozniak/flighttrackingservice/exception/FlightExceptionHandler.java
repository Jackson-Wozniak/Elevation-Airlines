package github.wozniak.flighttrackingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

@ControllerAdvice
public class FlightExceptionHandler {

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<String> airportNotFoundException(AirportNotFoundException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<String> dateFormatException(DateFormatException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> dateTimeParseException(DateTimeParseException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<String> flightNotFoundException(FlightNotFoundException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(FlightQueryException.class)
    public ResponseEntity<String> flightQueryException(FlightQueryException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(PlaneNotFoundException.class)
    public ResponseEntity<String> planeNotFoundException(PlaneNotFoundException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(RouteGeneratorException.class)
    public ResponseEntity<String> routeGeneratorException(RouteGeneratorException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(RouteSchedulingException.class)
    public ResponseEntity<String> routeSchedulingException(RouteSchedulingException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        String providedType = ex.getValue() != null ? ex.getValue().getClass().getSimpleName() : "null";
        String error = String.format("Expected type '%s' but input was type '%s'",
                Objects.requireNonNull(ex.getRequiredType()).getSimpleName(), providedType);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
