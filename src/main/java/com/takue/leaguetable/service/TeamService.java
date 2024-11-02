package com.takue.leaguetable.service;

import com.takue.leaguetable.Utils.TeamAlreadyExistsException;
import com.takue.leaguetable.Utils.TeamDoesNotExist;
import com.takue.leaguetable.model.Team;
import com.takue.leaguetable.model.TeamStatistics;
import com.takue.leaguetable.repository.TeamRepository;
import com.takue.leaguetable.repository.TeamStatisticsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class TeamService {
  private final TeamRepository teamRepository;
  private final TeamStatisticsRepository teamStatisticsRepository;

  public TeamService(TeamRepository teamRepository, TeamStatisticsRepository teamStatisticsRepository) {
    this.teamRepository = teamRepository;
    this.teamStatisticsRepository = teamStatisticsRepository;
  }

  public void registerTeam(Team team) {
    Team teamExist = teamRepository.findTeamByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(team.getTeamName(), team.getLeagueDivision(), team.getSeason(), team.getLeagueCup());
    if (teamExist != null) {
      throw new TeamAlreadyExistsException("A team with the name '" + team.getTeamName() + "' is already registered.");
    } else {
      teamRepository.save(team);
      TeamStatistics teamStatistics = new TeamStatistics();
      teamStatistics.setTeamName(team.getTeamName());
      teamStatistics.setLeagueCup(team.getLeagueCup());
      teamStatistics.setSeason(team.getSeason());
      teamStatistics.setLeagueDivision(team.getLeagueDivision());
      teamStatisticsRepository.save(teamStatistics);
    }

  }

  public void deleteTeam(Team team) {
    Team teamToDelete = teamRepository.findTeamByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(team.getTeamName(), team.getLeagueDivision(),team.getSeason(), team.getLeagueCup());
    if (teamToDelete.getLeagueCup() != null) {
      teamStatisticsRepository.deleteByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(
        team.getTeamName(), team.getLeagueDivision(), team.getSeason(), team.getLeagueCup()
      );
      teamRepository.delete(team);
    } else {
      throw new TeamDoesNotExist("A team with the name '" + team.getTeamName() + "' does not exist.");
    }
  }


    @Transactional
    public void updateTeamByAttributes(String teamName, String leagueDivision, String season, String leagueCup, Map<String, Object> updates) {
      // Find the Team entity by the specified attributes
      Team team = teamRepository.findTeamByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(teamName, leagueDivision, season, leagueCup);
      if (team == null) {
        throw new IllegalArgumentException("No team found with the specified attributes.");
      }
      // Update each field based on the updates map
      updates.forEach((fieldName, fieldValue) -> {
        try {
          Field field = Team.class.getDeclaredField(fieldName);
          field.setAccessible(true);
          field.set(team, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
          throw new IllegalArgumentException("Failed to update field '" + fieldName + "': " + e.getMessage());
        }
      });
      // Save the updated entity
      teamRepository.save(team);
    }
  }






