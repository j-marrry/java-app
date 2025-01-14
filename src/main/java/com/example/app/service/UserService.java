package com.example.app.service;

import com.example.app.dao.InMemoryUserDao;
import com.example.app.domain.User;

import java.util.List;

public interface UserService {
    public void create(String username, String password, String email, String lastname, String firstname, String patronymic, String birthday, String role);
    public List<User> findAll();
    public User findByUsername(String username);
    public void delete(int id);
    void update(int id, String username, String password, String email, String lastname, String firstname, String patronymic, String birthday, String role);
    boolean checkPassword(String username, String oldPassword);
    void changePassword(String username, String newPassword);
}
