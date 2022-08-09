package springinflearnstudy.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springinflearnstudy.study.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long> {



}
