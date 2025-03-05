package com.example.app.controller;

import com.example.app.domain.PasswordChangeForm;
import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("/passchange.jhtml")
public class PasschangeController {
    private final UserService userService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @Autowired
    public PasschangeController(UserService userService, MessageSource messageSource, LocaleResolver localeResolver) {
        this.userService = userService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @GetMapping
    public String showPasschangeForm(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "passchange";
    }

    @PostMapping
    public String changePassword(@AuthenticationPrincipal User userDetails,
                                 @Valid @ModelAttribute("passwordChangeForm") PasswordChangeForm form,
                                 BindingResult bindingResult,
                                 @RequestParam("action") String action,
                                 Model model,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {

        Locale locale = localeResolver.resolveLocale(request);

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/passchange.jhtml";
        }

        String username = userDetails.getUsername();
        String changeStr = messageSource.getMessage("passchange.change", null, locale);
        if(changeStr.equals(action)){
            if (username != null && form.getOldPassword() != null && form.getNewPassword() != null) {
                if (userService.checkPassword(username, form.getOldPassword())) {
                    if (Objects.equals(form.getConfirmPassword(), form.getNewPassword())) {
                        userService.changePassword(username, form.getNewPassword());
                        return "redirect:/welcome.jhtml";
                    } else {
                        String errorMessage = messageSource.getMessage("passchange.error-not-eq", null, locale);
                        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                        return "redirect:/passchange.jhtml";
                    }
                } else {
                    String errorMessage = messageSource.getMessage("passchange.error-old-pass", null, locale);
                    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                    return "redirect:/passchange.jhtml";
                }
            }
        }
        return "redirect:/welcome.jhtml";
    }
}
