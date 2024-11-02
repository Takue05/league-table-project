package com.takue.leaguetable.Utils;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
public class ApplicationLogger {
  private static final Logger log = LoggerFactory.getLogger(ApplicationLogger.class);

  public static void logStartupMessages() {
    log.info("*********************************************************************************");
    log.info("APPLICATION INITIALIZATION STARTED.");
    log.info("*********************************************************************************");

    // You can add more custom log messages here
    log.info("APPLICATION IS STARTING COMPLETELY. CHECKING DATABASE CONNECTIONS AND RESOURCES.");

    log.info("*********************************************************************************");
    log.info("APPLICATION STARTED SUCCESSFULLY.");
    log.info("*********************************************************************************");
  }
}

