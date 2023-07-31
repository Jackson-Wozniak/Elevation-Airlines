package github.wozniak.bankingservice.repository;

import github.wozniak.bankingservice.entity.RegistrationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

    Optional<RegistrationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM jwt_tokens t " +
            "WHERE t.expires_at <= :now", nativeQuery = true)
    void deleteExpiredTokens(LocalDateTime now);

    @Query(value = "SELECT ROW_COUNT() as DelRowCount", nativeQuery = true)
    int findDeletedRowCount();
}
