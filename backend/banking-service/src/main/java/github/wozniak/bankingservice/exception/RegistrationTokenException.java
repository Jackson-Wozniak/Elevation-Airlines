package github.wozniak.bankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistrationTokenException extends RuntimeException{
    public RegistrationTokenException(String message){
        super(message);
    }
}
