package springsecuritytutorial.tutorial.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(0)
@RequiredArgsConstructor
public class SecurityConfig06 {

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest().authenticated()
        .and()
                .httpBasic();

        return http.build();
    }

}
@Configuration
@Order(1)
@RequiredArgsConstructor
class SecurityConfig07 {

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception{
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin();

        return http.build();
    }

}
