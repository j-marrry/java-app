package com.example.app.controller;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WelcomeController {

    @GetMapping("/welcome.jhtml")
    public String welcomePage(@AuthenticationPrincipal User userDetails,
                              @RequestParam(value = "user", required = false) String user,
                              @RequestParam(value = "action", required = false) String action,
                              Model model) {
        if (action != null) {
            switch (action) {
                case "main":
                    return "redirect:/welcome.jhtml";
                case "users":
                    return "redirect:/users.jhtml";
                case "passchange":
                    return "redirect:/passchange.jhtml";
            }
        }

        return "welcome";
    }

    @PostMapping("/welcome.jhtml")
    public String handlePost(@RequestParam(value = "action", required = false) String action,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        if ("ВЫЙТИ".equals(action)) {
            session.invalidate();
            return "redirect:/login.jhtml";
        }
        return "redirect:/welcome.jhtml";
    }
}
