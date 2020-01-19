package com.gmail.jahont.pavel.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.jahont.pavel.repository.ConnectionRepository;
import com.gmail.jahont.pavel.repository.UserGroupRepository;
import com.gmail.jahont.pavel.repository.UserInformationRepository;
import com.gmail.jahont.pavel.repository.UserRepository;
import com.gmail.jahont.pavel.repository.constant.DatabaseConstant;
import com.gmail.jahont.pavel.repository.impl.ConnectionRepositoryImpl;
import com.gmail.jahont.pavel.repository.impl.UserGroupRepositoryImpl;
import com.gmail.jahont.pavel.repository.impl.UserInformationRepositoryImpl;
import com.gmail.jahont.pavel.repository.impl.UserRepositoryImpl;
import com.gmail.jahont.pavel.service.DatabaseHomeworkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseHomeworkServiceImpl implements DatabaseHomeworkService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private UserGroupRepository userGroupRepository = UserGroupRepositoryImpl.getInstance();
    private UserInformationRepository userInformationRepository = UserInformationRepositoryImpl.getInstance();

    @Override
    public void dropTableByName(String name) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                switch (name) {
                    case DatabaseConstant.USER_TABLE:
                        userRepository.dropTable(connection);
                        break;
                    case DatabaseConstant.USER_GROUP_TABLE:
                        userGroupRepository.dropTable(connection);
                        break;
                    case DatabaseConstant.USER_INFORMATION_TABLE:
                        userInformationRepository.dropTable(connection);
                        break;
                    default:
                        throw new IllegalArgumentException("Table with name " + name + " doesn't exist.");
                }
                connection.commit();
                logger.info("Table has dropped.");
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void createTableByName(String name) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                switch (name) {
                    case DatabaseConstant.USER_TABLE:
                        userRepository.createTable(connection);
                        break;
                    case DatabaseConstant.USER_GROUP_TABLE:
                        userGroupRepository.createTable(connection);
                        break;
                    case DatabaseConstant.USER_INFORMATION_TABLE:
                        userInformationRepository.createTable(connection);
                        break;
                    default:
                        throw new IllegalArgumentException("Table with name " + name + " hasn't created.");
                }
                connection.commit();
                logger.info("Table " + name + "has created.");
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}