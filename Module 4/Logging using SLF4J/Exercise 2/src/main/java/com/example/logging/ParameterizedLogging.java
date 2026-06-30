package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String username = "Alice";
        int loginAttempts = 3;
        
        // Parameterized logging with multiple parameters
        logger.info("User {} failed to login {} times", username, loginAttempts);
        logger.warn("Account for user {} has been locked temporarily", username);
        logger.debug("System details: os = {}, java_version = {}", System.getProperty("os.name"), System.getProperty("java.version"));
    }
}
