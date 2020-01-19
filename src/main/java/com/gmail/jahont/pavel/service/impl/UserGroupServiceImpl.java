package com.gmail.jahont.pavel.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.gmail.jahont.pavel.repository.ConnectionRepository;
import com.gmail.jahont.pavel.repository.UserGroupRepository;
import com.gmail.jahont.pavel.repository.impl.ConnectionRepositoryImpl;
import com.gmail.jahont.pavel.repository.impl.UserGroupRepositoryImpl;
import com.gmail.jahont.pavel.repository.model.UserGroup;
import com.gmail.jahont.pavel.service.UserGroupService;
import com.gmail.jahont.pavel.service.model.AddUserGroupDTO;
import com.gmail.jahont.pavel.service.model.UserGroupDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserGroupServiceImpl implements UserGroupService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private UserGroupRepository userGroupRepository = UserGroupRepositoryImpl.getInstance();

    @Override
    public UserGroupDTO add(AddUserGroupDTO addUserGroupDTO) {
        UserGroupDTO userGroupDTO = null;
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                UserGroup userGroup = convertDTOToUserGroup(addUserGroupDTO);
                userGroup = userGroupRepository.add(connection, userGroup);
                userGroupDTO = convertUserGroupToDTO(userGroup);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return userGroupDTO;
    }

    @Override
    public Map<Integer, UserGroup> findAll() {
        Map<Integer, UserGroup> userGroup = null;
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                userGroup = userGroupRepository.findAll(connection);
                connection.commit();
                return userGroup;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return userGroup;
    }

    private UserGroup convertDTOToUserGroup(AddUserGroupDTO userGroupDTO) {
        return UserGroup.newBuilder()
                .id(userGroupDTO.getId())
                .name(userGroupDTO.getName())
                .build();
    }

    private UserGroupDTO convertUserGroupToDTO(UserGroup userGroup) {
        UserGroupDTO userGroupDTO = new UserGroupDTO();
        userGroupDTO.setId(userGroup.getId());
        userGroupDTO.setName(userGroup.getName());
        return userGroupDTO;
    }
}
