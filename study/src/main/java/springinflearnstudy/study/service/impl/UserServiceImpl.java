package springinflearnstudy.study.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springinflearnstudy.study.domain.Account;
import springinflearnstudy.study.repository.UserRepository;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
