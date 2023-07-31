package github.wozniak.bankingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Entity(name = "jwtToken")
@Table(name = "jwt_tokens")
@Getter
@Setter
@NoArgsConstructor
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(name = "account_username", nullable = false)
    private BankAccount bankAccount;

    public RegistrationToken(BankAccount bankAccount, String token, LocalDateTime createdAt, boolean isTokenExtended){
        this.bankAccount = bankAccount;
        this.token = token;
        this.createdAt = createdAt;
        //extended tokens last 15 minutes, default tokens last 3
        this.expiresAt = createdAt.plusMinutes(isTokenExtended ? 15 : 3);
    }
}
