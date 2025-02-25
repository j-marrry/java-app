package com.example.app.dao;

import com.example.app.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    User read(int id);
    User findByUsername(String username);
    List<User> findAll();
    void create(User user);
    void update(User user);
    void delete(int id);
}
