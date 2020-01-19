package com.gmail.jahont.pavel.controller.impl;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import com.gmail.jahont.pavel.controller.HomeWorkController;
import com.gmail.jahont.pavel.repository.constant.DatabaseConstant;
import com.gmail.jahont.pavel.service.UserGroupService;
import com.gmail.jahont.pavel.service.impl.DatabaseHomeworkServiceImpl;
import com.gmail.jahont.pavel.service.impl.UserGroupServiceImpl;
import com.gmail.jahont.pavel.service.model.AddUserGroupDTO;
import com.gmail.jahont.pavel.service.model.UserGroupDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeWorkControllerImpl implements HomeWorkController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private DatabaseHomeworkServiceImpl databaseHomeworkService = new DatabaseHomeworkServiceImpl();

    @Override
    public void runFirstTask() {
        databaseHomeworkService.dropTableByName(DatabaseConstant.USER_INFORMATION_TABLE);
        databaseHomeworkService.dropTableByName(DatabaseConstant.USER_TABLE);
        databaseHomeworkService.dropTableByName(DatabaseConstant.USER_GROUP_TABLE);

        databaseHomeworkService.createTableByName(DatabaseConstant.USER_GROUP_TABLE);
        databaseHomeworkService.createTableByName(DatabaseConstant.USER_TABLE);
        databaseHomeworkService.createTableByName(DatabaseConstant.USER_INFORMATION_TABLE);

        UserGroupService userGroupService = new UserGroupServiceImpl();

        List<AddUserGroupDTO> addUserGroupDTOs = new ArrayList<>();
        int count = 3;
        for (int i = 0; i < count; i++) {
            AddUserGroupDTO addUserGroupDTO = new AddUserGroupDTO();
            addUserGroupDTO.setName("Test" + i);
            addUserGroupDTOs.add(addUserGroupDTO);
            UserGroupDTO userGroupDTO = userGroupService.add(addUserGroupDTO);
        }
        addUserGroupDTOs.forEach(logger::info);

    }
}
