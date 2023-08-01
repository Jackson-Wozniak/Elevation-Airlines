package github.wozniak.bankingservice.service;

import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.exception.RegistrationException;
import github.wozniak.bankingservice.repository.BankAccountRepository;
import github.wozniak.bankingservice.request.LoginRequest;
import github.wozniak.bankingservice.request.RegistrationRequest;
import github.wozniak.bankingservice.utils.PasswordValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Service
@AllArgsConstructor
public class RegistrationService {

    private final BankAccountService bankAccountService;

    public String register(RegistrationRequest registrationRequest){
        return bankAccountService.saveNewAccount(registrationRequest);
    }
}
