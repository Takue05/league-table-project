package com.takue.leaguetable.Utils;

public class TeamDoesNotExist extends RuntimeException {
  public TeamDoesNotExist(String message) {
    super(message);
  }
}
