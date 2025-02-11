package com.example.app.dao;

import com.example.app.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryUserDao implements UserDao{

    private static InMemoryUserDao instance;
    private final List<User> users = new ArrayList<>();

    private InMemoryUserDao() {
        try {
            users.add(new User(1, "user1", "password1", "user1@mail.ru", "lastname1", "firstname1", "patronymic1", "2001-01-01", Arrays.asList("admin")));
            users.add(new User(2, "user2", "password2", "user2@mail.ru", "lastname2", "firstname2", "patronymic2", "2002-02-02", Arrays.asList("user", "moderator")));
        } catch (Exception e) {
            throw new RuntimeException("Error initializing InMemoryUserDao", e);
        }
    }

    public static synchronized InMemoryUserDao getInstance() {
        if (instance == null) {
            instance = new InMemoryUserDao();
        }
        return instance;
    }

    @Override
    public User read(int id) {
        for (User user : users) {
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void update(User updatedUser) {
        for (User user : users) {
            if(user.getId() == updatedUser.getId()){
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setEmail(updatedUser.getEmail());
                user.setFirstname(updatedUser.getFirstname());
                user.setLastname(updatedUser.getLastname());
                user.setPatronymic(updatedUser.getPatronymic());
                user.setBirthday(updatedUser.getBirthday());
                user.setRoles(new ArrayList<>(updatedUser.getRoles()));
            }
        }
    }

    @Override
    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }

}
