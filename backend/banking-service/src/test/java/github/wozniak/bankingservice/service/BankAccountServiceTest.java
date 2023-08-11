package github.wozniak.bankingservice.service;

import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.exception.TransactionException;
import github.wozniak.bankingservice.request.DepositRequest;
import github.wozniak.bankingservice.request.WithdrawalRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Jackson Wozniak
 * @created : 8/11/2023, Friday
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
class BankAccountServiceTest {

    private final BankAccountService bankAccountService;

    @Autowired
    BankAccountServiceTest(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Test
    void depositFunds(){
        BankAccount test = new BankAccount("Test", "Test");
        test.setBalance(1000.0);

        DepositRequest request = new DepositRequest(1000.0, "TEST");

        assertDoesNotThrow(() -> bankAccountService.depositFunds(request, test));

        DepositRequest request2 = new DepositRequest(0.0, "TEST");
        assertThrows(TransactionException.class, () -> bankAccountService.depositFunds(request2, test));

        DepositRequest request3 = new DepositRequest(-10.0, "TEST");
        assertThrows(TransactionException.class, () -> bankAccountService.depositFunds(request3, test));
    }

    @Test
    void withdrawFunds(){
        BankAccount test = new BankAccount("Test", "Test");
        assertThrows(TransactionException.class, () -> bankAccountService.withdrawFunds(
                new WithdrawalRequest(100.0, "TEST"), test));

        test.setBalance(1000.0);
        assertDoesNotThrow(() -> bankAccountService.withdrawFunds(
                new WithdrawalRequest(100.0, "TEST"), test));

        WithdrawalRequest request2 = new WithdrawalRequest(0.0, "TEST");
        assertThrows(TransactionException.class, () -> bankAccountService.withdrawFunds(request2, test));

        WithdrawalRequest request3 = new WithdrawalRequest(-10000.0, "TEST");
        assertThrows(TransactionException.class, () -> bankAccountService.withdrawFunds(request3, test));
    }
}