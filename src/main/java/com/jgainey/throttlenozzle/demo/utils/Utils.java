package com.jgainey.throttlenozzle.demo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class Utils {

    public static Logger LOGGER;

    enum LOGTYPE{INFO,WARNING,ERROR}

    public static void initLogger(){
        LOGGER = LoggerFactory.getLogger(Utils.class);

        logInfo("Throttle-Nozzle Logger Ready");
    }

    public static void log(LOGTYPE type, String message) {
        switch (type){

            case INFO:
                logInfo(message);
                break;
            case WARNING:
                logWarning(message);
                break;
            case ERROR:
                logError(message);
                break;
            default:

        }
    }

    public static void logInfo(String message){
        LOGGER.info(message);
    }

    public static void logWarning(String message){
        LOGGER.warn(message);
    }

    public static void logError(String message){
        LOGGER.error(message);
    }

}
