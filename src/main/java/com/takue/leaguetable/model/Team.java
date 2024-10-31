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
  private Long teamId;
  private String teamName;
  private String coach;
  private String stadium;
  private String foundedYear;
}

