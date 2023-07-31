package github.wozniak.bankingservice.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Getter
@Setter
public class DepositRequest{

    private final String token;
    private final double transactionValue;

    public DepositRequest(double transactionValue, String token){
        this.transactionValue = transactionValue;
        this.token = token;
    }
}
