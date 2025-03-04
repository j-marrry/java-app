package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/login.jhtml")
public class LoginController {
    private final UserService userService;
    private final MessageSource messageSource;
    private final HttpServletRequest request;

    @Autowired
    public LoginController(UserService userService, MessageSource messageSource, HttpServletRequest httpServletRequest, HttpServletRequest request) {
        this.userService = userService;
        this.messageSource = messageSource;
        this.request = request;
    }

    @GetMapping
    public String showLoginForm(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @PostMapping
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        if(userService.checkPassword(login, password)) {
            User currentUser = userService.findByUsername(login);
            session.setAttribute("username", currentUser.getUsername());
            session.setAttribute("password", currentUser.getPassword());
            session.setAttribute("email", currentUser.getEmail());
            session.setAttribute("lastname", currentUser.getLastname());
            session.setAttribute("firstname", currentUser.getFirstname());
            session.setAttribute("patronymic", currentUser.getPatronymic());
            session.setAttribute("birthday", currentUser.getBirthday());
            session.setAttribute("roles", currentUser.getRoles());

            return "redirect:/welcome.jhtml";
        }
        else {
            Locale locale = (Locale) session.getAttribute("lang");
            if (locale == null) {
                locale = Locale.getDefault();
            }

            String errorMessage = messageSource.getMessage("login.errorMessage", null, locale);
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
    }
}
