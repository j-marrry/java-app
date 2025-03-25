package com.example.app.service;

import com.example.app.domain.User;

import java.util.List;

public interface UserService {
    public void create(User user);
    public List<User> findAll();
    public User findByUsername(String username);
    public User findById(int id);
    public boolean delete(int id);
    boolean update(int id, User user);
    boolean checkPassword(int id, String oldPassword);
    boolean changePassword(int id, String newPassword);
}
