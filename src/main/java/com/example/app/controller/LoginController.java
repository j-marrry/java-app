package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login.jhtml")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm() {
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
            model.addAttribute("errorMessage", "Неверное имя пользователя или пароль");
            return "login";
        }
    }
}
