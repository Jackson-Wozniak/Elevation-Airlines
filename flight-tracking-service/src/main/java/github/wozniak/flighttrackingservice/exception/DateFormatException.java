package github.wozniak.flighttrackingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DateFormatException extends RuntimeException{

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public DateFormatException(String message, boolean fullMessage){
        super(fullMessage ? message : incorrectDateToMessage(message));
    }

    private static String incorrectDateToMessage(String incorrectDate){
        return "Incorrect date format in {" + incorrectDate + "}. Must be in format MM/DD/YYYY";
    }
}
