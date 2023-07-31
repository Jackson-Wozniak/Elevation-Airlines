package github.wozniak.bankingservice.service;

import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.entity.RegistrationToken;
import github.wozniak.bankingservice.exception.BankAccountException;
import github.wozniak.bankingservice.exception.RegistrationException;
import github.wozniak.bankingservice.exception.TransactionException;
import github.wozniak.bankingservice.repository.BankAccountRepository;
import github.wozniak.bankingservice.request.DepositRequest;
import github.wozniak.bankingservice.request.RegistrationRequest;
import github.wozniak.bankingservice.request.WithdrawalRequest;
import github.wozniak.bankingservice.utils.PasswordValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Jackson Wozniak
 * @created : 7/28/2023, Friday
 **/
@Service
@AllArgsConstructor
public class BankAccountService implements UserDetailsService {

    private final BankAccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RegistrationTokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findById(username)
                .orElseThrow(() -> new BankAccountException("Cannot find account linked to username"));
    }

    public void depositFunds(DepositRequest request, BankAccount account){
        if(!account.isValidDeposit(request.getTransactionValue())){
            throw new TransactionException("Invalid deposit value");
        }
        account.addBalance(request.getTransactionValue());
        accountRepository.save(account);
    }

    public void withdrawFunds(WithdrawalRequest request, BankAccount account){
        if(!account.isValidWithdrawal(request.getTransactionValue())){
            throw new TransactionException("Invalid withdrawal value");
        }
        account.subtractBalance(request.getTransactionValue());
        accountRepository.save(account);
    }

    public boolean accountExists(String username){
        return accountRepository.findById(username).isPresent();
    }

    public String saveNewAccount(RegistrationRequest request) {
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
            throw new RegistrationException("All fields must be filled!");
        }
        if(accountExists(request.getUsername())){
            throw new RegistrationException("username is taken");
        }
        if(!PasswordValidator.isValid(request.getPassword())){
            throw new RegistrationException("password does not contain necessary characters");
        }
        String encodedPassword = passwordEncoder
                .encode(request.getPassword());

        BankAccount account = new BankAccount(request.getUsername(), encodedPassword);
        accountRepository.save(account);

        RegistrationToken confirmationToken = tokenService.generateToken(account, false);

        tokenService.saveConfirmationToken(confirmationToken);

        return confirmationToken.getToken();
    }
}
