package com.example.oauth2_google.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
public class UserController {

    @GetMapping("/")
    public String index() {
        return "Benvenuto! <a href='/oauth2/authorization/google'>Accedi con Google</a>";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return "redirect:/"; // Redirigi alla homepage se non autenticato
        }
        return "Ciao, " + principal.getAttribute("name") + "!";
    }

    @GetMapping("/logout")
    public String logoutSuccess() {
        return "Sei stato disconnesso. Torna alla <a href='/'>homepage</a>.";
    }
}