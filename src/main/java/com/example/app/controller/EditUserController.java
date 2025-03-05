package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
                                   Model model) {

        User user = userService.findByUsername(username);

        if ("delete".equals(action)) {
            userService.delete(user.getId());
            return "redirect:/login.jhtml";
        }

        model.addAttribute("user", user);
        model.addAttribute("rolesList", List.of("admin", "user", "manager"));

        return "edit_user";
    }

    @PostMapping
    public String editUser(@AuthenticationPrincipal User userDetails,
                           @Valid @ModelAttribute("user") User user,
                           BindingResult result, Model model,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("rolesList", List.of("admin", "user", "manager"));
            String errorMessage = result.getFieldError().getDefaultMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "edit_user";
        }

        userService.update(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getLastname(), user.getFirstname(), user.getPatronymic(), user.getBirthday(), user.getRoles());

        if (userDetails.getId()==user.getId()) {
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/login.jhtml";
        }

        return "redirect:/users.jhtml";
    }
}
