package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddUserController {

    private final UserService userService;

    @Autowired
    public AddUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adduser.jhtml")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("rolesList", List.of("admin", "user", "manager"));
        return "add_user";
    }

    @PostMapping("/adduser.jhtml")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            model.addAttribute("rolesList", List.of("admin", "user", "manager"));
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "add_user";
        }

        userService.create(user.getUsername(), user.getPassword(), user.getEmail(), user.getLastname(), user.getFirstname(), user.getPatronymic(), user.getBirthday(), user.getRoles());

        return "redirect:/users.jhtml";
    }
}
