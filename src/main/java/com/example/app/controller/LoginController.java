package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping("/login.jhtml")
public class LoginController {
    private final UserService userService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @Autowired
    public LoginController(UserService userService, MessageSource messageSource, LocaleResolver localeResolver) {
        this.userService = userService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @GetMapping
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                Model model,
                                HttpServletRequest request) {
        if (error != null) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = messageSource.getMessage("login.errorMessage", null, locale);
            model.addAttribute("errorMessage", errorMessage);
        }
        return "login";
    }
}
