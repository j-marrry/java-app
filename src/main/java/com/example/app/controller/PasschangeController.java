package com.example.app.controller;

import com.example.app.domain.PasswordChangeForm;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/passchange.jhtml")
public class PasschangeController {
    private final UserService userService;

    @Autowired
    public PasschangeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showPasschangeForm(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "passchange";
    }

    @PostMapping
    public String changePassword(@ModelAttribute("passwordChangeForm") PasswordChangeForm form,
                                 @RequestParam("action") String action,
                                 Model model, HttpSession session) {
        String username = session.getAttribute("username").toString();
        if("СМЕНИТЬ".equals(action)){
            if (username != null && form.getOldPassword() != null && form.getNewPassword() != null) {
                if (userService.checkPassword(username, form.getOldPassword())) {
                    if (Objects.equals(form.getConfirmPassword(), form.getNewPassword())) {
                        userService.changePassword(username, form.getNewPassword());
                        session.setAttribute("password", form.getNewPassword());
                        return "redirect:/welcome.jhtml";
                    } else {
                        model.addAttribute("errorMessage", "Пароли не совпадают");
                        return "passchange";
                    }
                } else {
                    model.addAttribute("errorMessage", "Старый пароль неверен");
                    return "passchange";
                }
            }
        }
        return "redirect:/welcome.jhtml";
    }
}
