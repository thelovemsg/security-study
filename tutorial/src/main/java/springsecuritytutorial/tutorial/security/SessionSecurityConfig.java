//package springsecuritytutorial.tutorial.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SessionSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//
//        http
//                .formLogin();
//
//        http
//                .sessionManagement()
////                .sessionFixation().none();
//                .sessionFixation().changeSessionId();
////                .maximumSessions(1)
////                .maxSessionsPreventsLogin(true);
//        return http.build();
//    }
//
//}
