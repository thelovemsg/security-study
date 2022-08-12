package springinflearnstudy.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import springinflearnstudy.study.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);


}
