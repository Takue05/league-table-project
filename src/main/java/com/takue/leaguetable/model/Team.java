package com.takue.leaguetable.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {
  @Id
  @GeneratedValue
  private Long teamId;
  private String teamName;
  private String coach;
  private String season;
  private String leagueCup;
  private String leagueDivision;
}

