package com.example.app.domain;

import java.time.LocalDate;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String lastname;
    private String firstname;
    private String patronymic;
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
