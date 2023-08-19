package github.wozniak.flightbookingservice.entity;

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
 * @created : 8/19/2023, Saturday
 **/
@Entity(name = "user")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "milesEarned")
    private Integer milesEarned;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.milesEarned = 10000;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
