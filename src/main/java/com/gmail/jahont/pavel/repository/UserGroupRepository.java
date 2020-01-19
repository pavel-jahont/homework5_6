package com.gmail.jahont.pavel.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.jahont.pavel.repository.model.UserGroup;

public interface UserGroupRepository extends GeneralRepository<UserGroup> {

    void createTable(Connection connection) throws SQLException;

    void dropTable(Connection connection) throws SQLException;

    UserGroup findByName(Connection connection, String searchName) throws SQLException;
}
