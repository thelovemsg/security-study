package securityjwt.securityAndJwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import securityjwt.securityAndJwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
