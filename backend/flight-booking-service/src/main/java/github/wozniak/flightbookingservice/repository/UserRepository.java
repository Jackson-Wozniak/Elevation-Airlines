package github.wozniak.flightbookingservice.repository;

import github.wozniak.flightbookingservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Jackson Wozniak
 * @created : 8/19/2023, Saturday
 **/
public interface UserRepository extends JpaRepository<User, String> {
}
