package com.takue.leaguetable.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatistics {
  @Id
  @GeneratedValue
  private long id;
  private String teamName;
  private String homeGoals;
  private String awayGoals;
  private int gamesPlayed;
  private int gamesWon;
  private int gamesLost;
  private int gamesDrawn;
  private int goalAgainst;
  private int goalsFor;
  private int goalsDifference;
  private int points;
  private String league;
  private String leagueDivision;
  private String season;

}
