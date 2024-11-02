package com.takue.leaguetable.repository;



public interface TeamStatisticLog {

  String getTeamName();
  int getPosition();
  int getPoints();
  int getAwayGoals();
  int getHomeGoals();
  int getGamesDrawn();
  int getGamesLost();
  int getGamesWon();
  int getGoalAgainst();
  int getGoalsFor();
  int getGoalsDifference();
}

