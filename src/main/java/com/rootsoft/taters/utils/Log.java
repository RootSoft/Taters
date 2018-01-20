package com.rootsoft.taters.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {

    //Constants
    private static final Logger logger = LogManager.getLogger("Taters");

    public static void i(String message) {
        logger.info(message);
    }

    public static void e(String message) {
        logger.error(message);
    }

    public static void e(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void w(String message) {
        logger.warn(message);
    }

}
