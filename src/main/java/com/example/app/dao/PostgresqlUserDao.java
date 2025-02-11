package com.example.app.dao;

import com.example.app.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlUserDao implements UserDao {

    private static PostgresqlUserDao instance;

    public static synchronized PostgresqlUserDao getInstance() {
        if (instance == null) {
            instance = new PostgresqlUserDao();
        }
        return instance;
    }

    public static final String jdbcDriver = "org.postgresql.Driver";
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/app";
    private static final String jdbcUser = "postgres";
    private static final String jdbcPassword = "postgres";

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcDriver);
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    @Override
    public User read(int id) {
        String sql = "SELECT u.*, ARRAY(SELECT r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = u.id) AS roles FROM users u WHERE u.id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();

            if (r.next()) {
                return getUserFromResultSet(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT u.*, ARRAY(SELECT r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = u.id) AS roles FROM users u WHERE u.username = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try{
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, username);
            r = s.executeQuery();

            if (r.next()) {
                return getUserFromResultSet(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.*, ARRAY(SELECT r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = u.id) AS roles FROM users u";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try{
            c = getConnection();
            s = c.prepareStatement(sql);
            r = s.executeQuery();

            while (r.next()) {
                users.add(getUserFromResultSet(r));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (username, password, email, lastname, firstname, patronymic, birthday) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try{
            c = getConnection();
            s = c.prepareStatement(sql);

            s.setString(1, user.getUsername());
            s.setString(2, user.getPassword());
            s.setString(3, user.getEmail());
            s.setString(4, user.getLastname());
            s.setString(5, user.getFirstname());
            s.setString(6, user.getPatronymic());
            s.setDate(7, Date.valueOf(user.getBirthday()));

            s.executeUpdate();

            insertUserRoles(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User updatedUser) {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, lastname = ?, firstname = ?, patronymic = ?, birthday = ? WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;

        try {
            c = getConnection();
            s = c.prepareStatement(sql);

            s.setString(1, updatedUser.getUsername());
            s.setString(2, updatedUser.getPassword());
            s.setString(3, updatedUser.getEmail());
            s.setString(4, updatedUser.getLastname());
            s.setString(5, updatedUser.getFirstname());
            s.setString(6, updatedUser.getPatronymic());
            s.setDate(7, Date.valueOf(updatedUser.getBirthday()));
            s.setInt(8, updatedUser.getId());

            s.executeUpdate();

            deleteUserRoles(updatedUser.getId());
            insertUserRoles(updatedUser);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;

        try {
            c = getConnection();
            s = c.prepareStatement(sql);

            s.setInt(1, id);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("lastname"),
                rs.getString("firstname"),
                rs.getString("patronymic"),
                rs.getDate("birthday").toString(),
                null
        );
        user.setRoles(List.of((String[]) rs.getArray("roles").getArray()));
        return user;
    }
    private void insertUserRoles(User user) throws SQLException {
        String sql = "INSERT INTO user_roles (user_id, role_id) VALUES (?, (SELECT id FROM roles WHERE name = ?))";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);

            for (String role : user.getRoles()) {
                s.setInt(1, user.getId());
                s.setString(2, role);
                s.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUserRoles(int userId) throws SQLException {
        String sql = "DELETE FROM user_roles WHERE user_id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);

            s.setInt(1, userId);
            s.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
