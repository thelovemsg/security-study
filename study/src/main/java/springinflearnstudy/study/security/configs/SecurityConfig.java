package springinflearnstudy.study.security.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springinflearnstudy.study.security.common.FormAuthenticationDetailsSource;
import springinflearnstudy.study.security.filter.AjaxLoginProcessingFilter;
import springinflearnstudy.study.security.handler.CustomAccessDeniedHandler;
import springinflearnstudy.study.security.handler.CustomAuthenticationFailureHandler;
import springinflearnstudy.study.security.handler.CustomAuthenticationSuccessHandler;
import springinflearnstudy.study.security.provider.CustomAuthenticationProvider;
import springinflearnstudy.study.security.service.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final FormAuthenticationDetailsSource authenticationDetailsSource;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(registry -> {
                    registry.antMatchers("/", "/users", "user/login/**", "/login*").permitAll()
                            .antMatchers("/mypage").hasRole("USER")
                            .antMatchers("/messages").hasRole("MANAGER")
                            .antMatchers("/config").hasRole("ADMIN")
                            .anyRequest().authenticated();

                }).formLogin(login -> {
                    login.loginPage("/login")
                            .loginProcessingUrl("/login_proc")
                            .defaultSuccessUrl("/")
                            .authenticationDetailsSource(authenticationDetailsSource)
                            .successHandler(authenticationSuccessHandler)
                            .failureHandler(authenticationFailureHandler)
                            .permitAll();
                }).exceptionHandling(exception -> {
                    exception.accessDeniedHandler(accessDeniedHandler());
                })
                .addFilterBefore(loginProcessingFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean("loginProcessingFilter")
    AjaxLoginProcessingFilter loginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter loginProcessingFilter = new AjaxLoginProcessingFilter();
        loginProcessingFilter.setAuthenticationManager(authenticationManager());
        return loginProcessingFilter;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
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
    CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

}
//
//class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
//    public MyCustomDsl() {
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//        http.addFilter(new AjaxLoginProcessingFilter(authenticationManager));
//    }
//
//    public static MyCustomDsl customDsl() {
//        return new MyCustomDsl();
//    }
//}