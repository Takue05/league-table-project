package com.takue.leaguetable.model;


import jakarta.persistence.Column;
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

  @Column(nullable = false, length = 100)
  private String teamName;
  private int homeGoals = 0;
  private int awayGoals = 0;
  private int gamesPlayed = 0;
  private int gamesWon = 0;
  private int gamesLost = 0;
  private int gamesDrawn = 0;
  private int goalAgainst = 0;
  private int goalsFor = 0;
  private int goalsDifference = 0;
  private int points = 0;
  private String leagueCup;
  private String leagueDivision;
  private String season;

}
