package com.example.app.service;

import com.example.app.dao.UserDao;
import com.example.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(String username, String password, String email, String lastname, String firstname, String patronymic, LocalDate birthday, List<String> roles) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, email, lastname, firstname, patronymic, birthday, roles);
        userDao.create(user);
        userDao.createUserRole(user);
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
    public void update(int id, String username, String password, String email, String lastname, String firstname, String patronymic, LocalDate birthday, List<String> roles) {
        User user = new User(id, username, password, email, lastname, firstname, patronymic, birthday, roles);
        userDao.update(user);
    }

    @Override
    public boolean checkPassword(String username, String oldPassword) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found for username: " + username);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userDao.update(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
        /*return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();*/
        return user;
    }
}
