package github.wozniak.bankingservice.service;

import github.wozniak.bankingservice.exception.BankAccountException;
import github.wozniak.bankingservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Jackson Wozniak
 * @created : 7/28/2023, Friday
 **/
@Service
@AllArgsConstructor
public class BankAccountService implements UserDetailsService {

    private final BankAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findById(username)
                .orElseThrow(() -> new BankAccountException("Cannot find account linked to username"));
    }
}
