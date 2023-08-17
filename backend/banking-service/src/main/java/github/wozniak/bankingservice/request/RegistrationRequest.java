package github.wozniak.bankingservice.request;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public String asJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
