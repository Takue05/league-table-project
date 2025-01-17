package com.takue.leaguetable.Utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {
  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent() {
    ApplicationLogger.logStartupMessages();
  }
}
