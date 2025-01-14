package com.example.app.service;

import com.example.app.dao.InMemoryUserDao;
import com.example.app.dao.UserDao;

public class ServiceFactory {
    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }
}
