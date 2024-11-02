package com.takue.leaguetable.controller;

import com.takue.leaguetable.Utils.TeamAlreadyExistsException;
import com.takue.leaguetable.Utils.TeamDoesNotExist;
import com.takue.leaguetable.model.Team;
import com.takue.leaguetable.model.TeamStatistics;
import com.takue.leaguetable.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
  @RequestMapping("teams/")
public class TeamController {


    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
      this.teamService = teamService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerTeam(@RequestBody Team team) {
      try {
        teamService.registerTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body("Team registered successfully.");
      } catch (TeamAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
      }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTeam(@RequestBody Team team) {
      try {
        teamService.deleteTeam(team);
        return ResponseEntity.ok("Team deleted successfully.");
      } catch (TeamDoesNotExist e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
    }

    @PutMapping("/{teamName}/{leagueDivision}/{season}/{leagueCup}")
    public ResponseEntity<String> updateTeam(
      @PathVariable String teamName,
      @PathVariable String leagueDivision,
      @PathVariable String season,
      @PathVariable String leagueCup,
      @RequestBody Map<String, Object> updates) {
      try {
        teamService.updateTeamByAttributes(teamName, leagueDivision, season, leagueCup, updates);
        return ResponseEntity.ok("Team updated successfully.");
      } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
    }


}
