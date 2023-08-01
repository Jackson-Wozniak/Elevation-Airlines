package github.wozniak.bankingservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Getter
@AllArgsConstructor
public class LoginRequest {

    private final String username;
    private final String password;
    private final boolean isExtendedSession;
}
