package springsecuritytutorial.tutorial.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@RequiredArgsConstructor
public class SecurityConfig3 {

//    private final UserDetailsService userDetailsService;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//
//        http
//                .formLogin();
//
//        http
//                .rememberMe()
//                .userDetailsService(userDetailsService);
//        return http.build();
//    }

}
