package com.example.app.domain;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {
    private int id;
    @NotNull(message = "{user.username-notnull}")
    @Size(min = 5, max = 20, message = "{user.username-size}")
    @Pattern(regexp = "[A-Za-z0-9]{5,20}", message = "{user.username-pattern}")
    private String username;
    @NotNull(message = "{user.password-notnull}")
    private String password;
    @NotNull(message = "{user.email-notnull}")
    @Email(message = "{user.email-invalid}")
    private String email;
    @NotNull(message = "{user.lastname-notnull}")
    @Size(min = 2, max = 25, message = "{user.lastname-size}")
    @Pattern(regexp = "[А-Яа-яЁёA-Za-z]{2,25}", message = "{user.lastname-pattern}")
    private String lastname;
    @NotNull(message = "{user.firstname-notnull}")
    @Size(min = 2, max = 25, message = "{user.firstname-size}")
    @Pattern(regexp = "[А-Яа-яЁёA-Za-z]{2,25}", message = "{user.firstname-pattern}")
    private String firstname;
    @Size(min = 2, max = 25, message = "{user.patronymic-size}")
    @Pattern(regexp = "[А-Яа-яЁёA-Za-z]{2,25}", message = "{user.patronymic-pattern}")
    private String patronymic;
    @NotNull(message = "{user.birthday-notnull}")
    @Past(message = "{user.birthday-past}")
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role.toUpperCase())
                .collect(Collectors.toList());
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
