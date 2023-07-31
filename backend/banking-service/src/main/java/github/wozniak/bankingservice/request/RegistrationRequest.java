package github.wozniak.bankingservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    private String username;
    private String password;
}
