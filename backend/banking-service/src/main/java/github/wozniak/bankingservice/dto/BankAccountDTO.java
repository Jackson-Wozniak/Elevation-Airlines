package github.wozniak.bankingservice.dto;

import github.wozniak.bankingservice.entity.BankAccount;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Getter
@Setter
public class BankAccountDTO {
    private final String username;
    private final double balance;

    public BankAccountDTO(BankAccount account){
        this.username = account.getUsername();
        this.balance = account.getBalance();
    }
}
