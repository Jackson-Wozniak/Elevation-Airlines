package github.wozniak.bankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Jackson Wozniak
 * @created : 7/28/2023, Friday
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "cannot complete transaction")
public class TransactionException extends RuntimeException{
    public TransactionException(String message){
        super(message);
    }
}
