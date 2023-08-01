package github.wozniak.bankingservice.service;

import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.entity.RegistrationToken;
import github.wozniak.bankingservice.exception.RegistrationTokenException;
import github.wozniak.bankingservice.repository.RegistrationTokenRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Service
@AllArgsConstructor
public class RegistrationTokenService {

    private final RegistrationTokenRepository tokenRepository;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTokenService.class);

    public void saveConfirmationToken(RegistrationToken token) {
        tokenRepository.save(token);
    }

    public RegistrationToken getToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new RegistrationTokenException("Cannot find matching account token"));
    }

    @Transactional
    public long deleteExpiredTokens(){
        tokenRepository.deleteExpiredTokens(LocalDateTime.now());
        return tokenRepository.findDeletedRowCount();
    }

    public RegistrationToken generateToken(BankAccount account, boolean isExtended){
        String token = UUID.randomUUID().toString();
        return new RegistrationToken(
                account,
                token,
                LocalDateTime.now(),
                isExtended
        );
    }
}
