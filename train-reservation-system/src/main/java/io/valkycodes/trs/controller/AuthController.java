package io.valkycodes.trs.controller;

import io.valkycodes.trs.context.AuthenticatedUserContext;
import io.valkycodes.trs.model.dto.UserData;
import io.valkycodes.trs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticatedUserContext authenticatedUserContext;

    @GetMapping("/")
    public String registerForm() {
        if (authenticatedUserContext.isAuthenticated())
            return "redirect:/home";
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserData userData) {
        authService.registerUser(userData);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        if (authenticatedUserContext.isAuthenticated())
            return "redirect:/home";
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        UserData userData = authService.loginUser(username, password);

        if (userData != null) {
            authenticatedUserContext.authenticateUser(userData);
            return "redirect:/home";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        authenticatedUserContext.resetUser();
        return "redirect:/";
    }
}
