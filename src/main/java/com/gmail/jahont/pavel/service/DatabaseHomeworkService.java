package com.gmail.jahont.pavel.service;

public interface DatabaseHomeworkService {

    void dropTableByName(String name);

    void createTableByName(String name);
}