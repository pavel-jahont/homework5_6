package com.gmail.jahont.pavel.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.jahont.pavel.repository.model.UserInformation;

public interface UserInformationRepository extends GeneralRepository<UserInformation> {

    void createTable(Connection connection) throws SQLException;

    void dropTable(Connection connection) throws SQLException;
}
