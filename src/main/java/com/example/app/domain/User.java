package com.example.app.domain;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class User {
    private int id;
    @NotNull(message = "Имя пользователя не может быть пустым")
    @Size(min = 5, max = 20, message = "Имя пользователя должно содержать от 5 до 20 символов")
    @Pattern(regexp = "[A-Za-z0-9]{5,20}", message = "Имя пользователя должно состоять только из английских букв и цифр")
    private String username;
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 20, message = "Пароль должен содержать от 8 до 20 символов")
    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}", message = "Пароль должен содержать хотя бы одну букву и цифру")
    private String password;
    @NotNull(message = "Почта не божет быть пустой")
    @Email
    private String email;
    @NotNull(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 25, message = "Фамилия должна содержать от 2 до 25 символов")
    @Pattern(regexp = "[А-Яа-яЁё]{2,25}", message = "Фамилия должна состоять только из русских букв")
    private String lastname;
    @NotNull(message = "Имя не может быть пустым")
    @Size(min = 2, max = 25, message = "Имя должно содержать от 2 до 25 символов")
    @Pattern(regexp = "[А-Яа-яЁё]{2,25}", message = "Имя должно состоять только из русских букв")
    private String firstname;
    @NotNull(message = "Отчество не может быть пустым")
    @Size(min = 2, max = 25, message = "Отчество должно содержать от 2 до 25 символов")
    @Pattern(regexp = "[А-Яа-яЁё]{2,25}", message = "Отчество должно состоять только из русских букв")
    private String patronymic;
    @NotNull(message = "Дата рождения не может быть пустой")
    @Past(message = "Некорректная дата рождения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private List<String> roles;

    public User(int id, String username, String password, String email, String lastname, String firstname, String patronymic, LocalDate birthday, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.roles = roles;
    }

    public User(String username, String password, String email, String lastname, String firstname, String patronymic, LocalDate birthday, List<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.roles = roles;
    }

    public User() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
