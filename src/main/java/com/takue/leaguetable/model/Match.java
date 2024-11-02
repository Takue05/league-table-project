package com.takue.leaguetable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String homeTeamName;
  private String awayTeamName;
  private LocalDate matchDate;
  private int awayTeamScore;
  private int homeTeamScore;
  private String stadium;
  private String referee ;
  private String  leagueCup;
  private String season;
  private String leagueDivision;




  }
