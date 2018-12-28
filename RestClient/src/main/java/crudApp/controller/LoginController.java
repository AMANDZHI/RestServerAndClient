package crudApp.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String home() {
    return "login";
}

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "login";
    }
}