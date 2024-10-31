package com.takue.leaguetable.service;

import com.takue.leaguetable.Utils.TeamAlreadyExistsException;
import com.takue.leaguetable.Utils.TeamDoesNotExist;
import com.takue.leaguetable.model.Team;
import com.takue.leaguetable.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class TeamService {
  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public void registerTeam(Team team) {
    if (teamRepository.existsById(team.getTeamId())) {
      throw new TeamAlreadyExistsException("A team with the name '" + team.getTeamName() + "' is already registered.");
    } else {
      teamRepository.save(team);
    }

  }

  public void deleteTeam(Team team) {
    if (teamRepository.findTeamByTeamId(team.getTeamId()) == null) {
      teamRepository.delete(team);
    } else {
      throw new TeamDoesNotExist("A team with the name '" + team.getTeamName() + "' does not exist.");
    }
  }


  public void updateTeam(Long id, Map<String, Object> updates) {
    Team existingTeam = teamRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Team with ID '" + id + "' does not exist."));

    updates.forEach((fieldName, fieldValue) -> {
      try {
        Field field = Team.class.getDeclaredField(fieldName);
        field.setAccessible(true);

        field.set(existingTeam, fieldValue);
      } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new IllegalArgumentException("Failed to update field '" + fieldName + "': " + e.getMessage());
      }
    });

    teamRepository.save(existingTeam);
  }



}


