package com.example.app.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PasswordChangeForm {
    @NotNull(message = "Старый пароль не может быть пустым")
    private String oldPassword;
    @NotNull(message = "Новый пароль не может быть пустым")
    @Size(min = 8, max = 20, message = "Пароль должен содержать от 8 до 20 символов")
    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}", message = "Пароль должен содержать буквы и цифры")
    private String newPassword;
    @NotNull(message = "Подтверждение пароля не может быть пустым")
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
