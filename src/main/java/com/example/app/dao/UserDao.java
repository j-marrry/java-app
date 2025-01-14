package com.example.app.dao;

import com.example.app.domain.User;

import java.util.List;

public interface UserDao {
    User read(int id);
    User findByUsername(String username);
    List<User> findAll();
    void create(User user);
    void update(User user);
    void delete(int id);
}
