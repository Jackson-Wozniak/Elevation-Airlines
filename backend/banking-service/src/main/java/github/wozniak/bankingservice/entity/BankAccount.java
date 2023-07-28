package github.wozniak.bankingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author : Jackson Wozniak
 * @created : 7/28/2023, Friday
 **/
@Entity(name = "bankAccount")
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
public class BankAccount implements UserDetails {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "reward_points")
    private Integer rewardPoints;

    public BankAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.rewardPoints = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
