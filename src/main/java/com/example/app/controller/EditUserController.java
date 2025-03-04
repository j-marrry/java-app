package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/edituser.jhtml")
public class EditUserController {

    private final UserService userService;

    @Autowired
    public EditUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showEditUserForm(@RequestParam("username") String username,
                                   @RequestParam(value = "action", required = false) String action,
                                   HttpSession session,
                                   Model model) {

        User user = userService.findByUsername(username);

        if ("delete".equals(action)) {
            if (username.equals(session.getAttribute("username"))) {
                session.invalidate();
            }
            userService.delete(user.getId());
            return "redirect:/users.jhtml";
        }

        model.addAttribute("user", user);
        model.addAttribute("rolesList", List.of("admin", "user", "manager"));

        return "edit_user";
    }

    @PostMapping
    public String editUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result, Model model,
                           HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("rolesList", List.of("admin", "user", "manager"));
            String errorMessage = result.getFieldError().getDefaultMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "edit_user";
        }

        userService.update(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getLastname(), user.getFirstname(), user.getPatronymic(), user.getBirthday(), user.getRoles());

        if (session.getAttribute("username").equals(user.getUsername())) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("lastname", user.getLastname());
            session.setAttribute("firstname", user.getFirstname());
            session.setAttribute("patronymic", user.getPatronymic());
            session.setAttribute("birthday", user.getBirthday());
            session.setAttribute("roles", user.getRoles());
        }

        return "redirect:/users.jhtml";
    }
}
