package springinflearnstudy.study.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
<<<<<<< HEAD
    public String login(){
=======
    public String login() {
>>>>>>> afb749fe9379e3ce2443b54c3805d99df3b6fcd4
        return "user/login/login";
    }

    @GetMapping("/logout")
<<<<<<< HEAD
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
=======
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication!= null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

>>>>>>> afb749fe9379e3ce2443b54c3805d99df3b6fcd4
        return "redirect:/login";
    }

}
