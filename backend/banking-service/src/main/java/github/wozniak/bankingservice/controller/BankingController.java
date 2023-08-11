package github.wozniak.bankingservice.controller;

import github.wozniak.bankingservice.dto.BankAccountDTO;
import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.request.DepositRequest;
import github.wozniak.bankingservice.request.WithdrawalRequest;
import github.wozniak.bankingservice.service.BankAccountService;
import github.wozniak.bankingservice.service.RegistrationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@RestController
@RequestMapping(value = "/api/v1/banking")
@AllArgsConstructor
public class BankingController {

    private final BankAccountService bankAccountService;
    private final RegistrationTokenService tokenService;

    @GetMapping(value = "/account")
    public BankAccountDTO getAccount(@RequestParam("token") String token){
        return new BankAccountDTO(tokenService.getToken(token).getBankAccount());
    }

    @PutMapping(value = "/deposit")
    public ResponseEntity<?> depositFunds(@RequestBody DepositRequest request){
        BankAccount account = tokenService.getToken(request.getToken()).getBankAccount();
        bankAccountService.depositFundsAndSave(request, account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/withdrawal")
    public ResponseEntity<?> withdrawFunds(@RequestBody WithdrawalRequest request){
        BankAccount account = tokenService.getToken(request.getToken()).getBankAccount();
        bankAccountService.withdrawFundsAndSave(request, account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
