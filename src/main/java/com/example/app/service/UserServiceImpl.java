package com.example.app.service;

import com.example.app.dao.UserDao;
import com.example.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(String username, String password, String email, String lastname, String firstname, String patronymic, String birthday, List<String> roles) {
        int id = (int)(Math.random() * 1000 + 1);
        User user = new User(id, username, password, email, lastname, firstname, patronymic, birthday, roles);
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
    public void update(int id, String username, String password, String email, String lastname, String firstname, String patronymic, String birthday, List<String> roles) {
        User user = new User(id, username, password, email, lastname, firstname, patronymic, birthday, roles);
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
        userDao.update(user);
    }
}
