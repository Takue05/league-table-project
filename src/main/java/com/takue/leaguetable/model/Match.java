package com.takue.leaguetable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


@Entity
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match{
  @Id
  @GeneratedValue
  private Long match_id;
  private int homeTeamId;
  private int awayTeamId;
  private Date matchDate;
  private int matchDAy;
  private int awayTeamScore;
  private int homeTeamScore;
  private String stadium;
  private String season;
  private String referee ;
  private String status;



  }
