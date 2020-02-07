package com.jgainey.throttlenozzle.demo.utils;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class Utils {

    public static Logger LOGGER;

    enum LOGTYPE{INFO,WARNING,ERROR}

    public static void initLogger(){
        LOGGER = Logger.getLogger("Throttle-Nozzle");
        LOGGER.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
        LOGGER.setLevel(Level.ALL);
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
        LOGGER.log(Level.INFO, message);
    }

    public static void logWarning(String message){
        LOGGER.log(Level.WARNING, message);
    }

    public static void logError(String message){
        LOGGER.log(Level.SEVERE, message);
    }

}
