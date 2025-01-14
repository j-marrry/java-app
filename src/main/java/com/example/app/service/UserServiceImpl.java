package com.example.app.service;

import com.example.app.dao.DaoFactory;
import com.example.app.dao.UserDao;
import com.example.app.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new DaoFactory().getUserDao();
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(String username, String password, String email, String lastname, String firstname, String patronymic, String birthday, String role) {
        int id = (int)(Math.random() * 1000 + 1);
        User user = new User(id, username, password, email, lastname, firstname, patronymic, birthday, role);
        userDao.create(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found for username: " + username);
        }
        return user;
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void update(int id, String username, String password, String email, String lastname, String firstname, String patronymic, String birthday, String role) {
        User user = new User(id, username, password, email, lastname, firstname, patronymic, birthday, role);
        userDao.update(user);
    }

    @Override
    public boolean checkPassword(String username, String oldPassword) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found for username: " + username);
        }
        return user.getPassword().equals(oldPassword);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found for username: " + username);
        }
        user.setPassword(newPassword);
    }
}
