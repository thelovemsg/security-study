package springinflearnstudy.study.security.configs;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(registry -> {
                    registry.antMatchers("/","/users").permitAll()
                            .antMatchers("/mypage").hasRole("USER")
                            .antMatchers("/messages").hasRole("MANAGER")
                            .antMatchers("/config").hasRole("ADMIN")
                            .anyRequest().authenticated();
                }).formLogin();

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring().antMatchers("/resources/**");
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        String password = passwordEncoder().encode("1111");
        User.UserBuilder users = User.builder();
        UserDetails user = users.username("user").password(password).roles("USER").build();
        UserDetails manager = users.username("manager").password(password).roles("MANAGER").build();
        UserDetails admin = users.username("admin").password(password).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, manager, admin);
    }

}
