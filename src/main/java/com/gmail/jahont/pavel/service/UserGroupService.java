package com.gmail.jahont.pavel.service;

import java.util.Map;

import com.gmail.jahont.pavel.service.model.AddUserGroupDTO;
import com.gmail.jahont.pavel.service.model.UserGroupDTO;

public interface UserGroupService<T> {

    UserGroupDTO add(AddUserGroupDTO userGroupDTO);

    Map<Integer, T> findAll();
}