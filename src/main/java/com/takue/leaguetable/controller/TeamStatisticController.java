package com.takue.leaguetable.controller;

import com.takue.leaguetable.model.TeamStatistics;
import com.takue.leaguetable.repository.TeamStatisticLog;
import com.takue.leaguetable.service.TeamStatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
  @RequestMapping("/statistics")
public class TeamStatisticController {
  private final TeamStatisticService teamStatisticService;

  public TeamStatisticController(TeamStatisticService teamStatisticService) {
    this.teamStatisticService = teamStatisticService;
  }

  @GetMapping("/log")
  public ResponseEntity<List<TeamStatisticLog>> getTeamStatisticsLog(
    @RequestParam String season,
    @RequestParam String leagueCup,
    @RequestParam String leagueDivision) {

    List<TeamStatisticLog> log = teamStatisticService.getLog(season, leagueCup, leagueDivision);
    return ResponseEntity.ok(log);
  }

  @GetMapping("team/history")
  public ResponseEntity<List<TeamStatistics>> getTeamStatisticsHistory(
    @RequestParam String teamName
  ) {
    List<TeamStatistics> teamHistory = teamStatisticService.getTeamStatisticHistory(teamName);
    return ResponseEntity.ok(teamHistory);
  }
}
