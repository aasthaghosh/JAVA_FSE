package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.info("Starting the application...");
        logger.debug("Debugging detailed log message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.info("Application finished execution.");
    }
}
