package github.wozniak.bankingservice.repository;

import github.wozniak.bankingservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Jackson Wozniak
 * @created : 7/28/2023, Friday
 **/
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
