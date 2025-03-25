package com.example.app.service;

import com.example.app.dao.UserDao;
import com.example.app.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void create(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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
    public User findById(int id) {
        return userDao.read(id);
    }

    @Override
    public boolean delete(int id) {
        userDao.delete(id);
        return true;
    }

    @Override
    public boolean update(int id, User user) {
        user.setId(id);
        userDao.update(user);
        return true;
    }

    @Override
    public boolean checkPassword(int id, String oldPassword) {
        User user = userDao.read(id);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public boolean changePassword(int id, String newPassword) {
        User user = userDao.read(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found for ID: " + id);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userDao.update(user);
        return true;
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
