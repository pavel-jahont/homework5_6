package com.gmail.jahont.pavel.repository.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.gmail.jahont.pavel.repository.UserRepository;
import com.gmail.jahont.pavel.repository.model.User;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS user (id INT(11) AUTO_INCREMENT PRIMARY KEY, username VARCHAR(40) NOT NULL," +
                        "password VARCHAR(40) NOT NULL, is_active TINYINT(1) DEFAULT 0, user_group_id INT(11)," +
                        "age INT(11)," +
                        "FOREIGN KEY(user_group_id) REFERENCES user_group(id))"
        )) {
            statement.execute();
        }
    }

    @Override
    public void dropTable(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DROP TABLE IF EXISTS user"
        )) {
            statement.execute();
        }
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user (username,password,is_active,user_group_id,age) " +
                        "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getActive());
            statement.setInt(4, user.getUserGroupId());
            statement.setInt(5, user.getAge());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return user;
        }
    }

    @Override
    public User get(Connection connection, Serializable id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM user WHERE id = ?"
        )) {
            statement.setInt(1, (int) id);
            try (ResultSet resultSet = statement.executeQuery()) {
                User user = null;
                if (resultSet.next()) {
                    user = User.newBuilder().id(
                            resultSet.getInt(1)).userName(resultSet.getString(2))
                            .password(resultSet.getString(3))
                            .isActive(resultSet.getBoolean(4))
                            .userGroupId(resultSet.getInt(5))
                            .age(resultSet.getInt(6))
                            .build();
                }
                return user;
            }
        }
    }

    @Override
    public void update(Connection connection, User user) throws SQLException {

    }

    @Override
    public int delete(Connection connection, Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public Map<Integer, User> findAll(Connection connection) throws SQLException {
        return null;
    }
}