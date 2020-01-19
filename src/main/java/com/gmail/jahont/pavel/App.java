package com.gmail.jahont.pavel;

import java.lang.invoke.MethodHandles;

import com.gmail.jahont.pavel.controller.impl.HomeWorkControllerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static HomeWorkControllerImpl homeWorkController = new HomeWorkControllerImpl();

    public static void main(String[] args) {
        homeWorkController.runFirstTask();
    }
}
