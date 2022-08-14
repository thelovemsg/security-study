package springinflearnstudy.study.security.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import springinflearnstudy.study.security.provider.CustomAuthenticationProvider;
import springinflearnstudy.study.security.service.CustomUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

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

                }).formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
<<<<<<< HEAD
                .defaultSuccessUrl("/")
                .successHandler(authenticationSuccessHandler)
=======
                .defaultSuccessUrl("/",true)
>>>>>>> afb749fe9379e3ce2443b54c3805d99df3b6fcd4
                .permitAll();

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring().antMatchers("/resources/**");
    }

    /*Explanation:
    In the old version you inject AuthenticationManagerBuilder, set userDetailsService, passwordEncoder and build it.
    But authenticationManager is already created in this step.
    It is created the way we wanted (with userDetailsService and the passwordEncoder).
    https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring
    */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

}
