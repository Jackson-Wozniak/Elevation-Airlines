package github.wozniak.bankingservice.controller;

import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.entity.RegistrationToken;
import github.wozniak.bankingservice.request.LoginRequest;
import github.wozniak.bankingservice.request.RegistrationRequest;
import github.wozniak.bankingservice.service.BankAccountService;
import github.wozniak.bankingservice.service.RegistrationService;
import github.wozniak.bankingservice.service.RegistrationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@RestController
@RequestMapping(value = "/api/v1/banking/account")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final RegistrationTokenService tokenService;
    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<?> registerNewAccount(@RequestBody RegistrationRequest registrationRequest){
        try{
            String jwtToken = registrationService.register(registrationRequest);
            return ResponseEntity.ok(jwtToken);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/login")
    public ResponseEntity<?> loginAsUser(@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));

            RegistrationToken token = tokenService.generateToken(
                    (BankAccount) bankAccountService.loadUserByUsername(authentication.getName()),
                    loginRequest.isExtendedSession()
            );

            tokenService.saveConfirmationToken(token);

            return ResponseEntity.ok(token.getToken());
        }catch (Exception ex){
            //give more specific error message
            if(ex.getMessage().equalsIgnoreCase("Bad credentials")){
                return new ResponseEntity<>("Incorrect Password", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
