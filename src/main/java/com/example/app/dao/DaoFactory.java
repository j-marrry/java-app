package com.example.app.dao;

public class DaoFactory {

    public UserDao getUserDao() {
        return PostgresqlUserDao.getInstance();
    }
}
