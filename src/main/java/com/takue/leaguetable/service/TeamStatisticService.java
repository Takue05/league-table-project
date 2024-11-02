package com.takue.leaguetable.service;

import com.takue.leaguetable.model.TeamStatistics;
import com.takue.leaguetable.repository.TeamStatisticLog;
import com.takue.leaguetable.repository.TeamStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamStatisticService {

  private final TeamStatisticsRepository teamStatisticsRepository;

  @Autowired
  public TeamStatisticService(TeamStatisticsRepository teamStatisticsRepository) {
    this.teamStatisticsRepository = teamStatisticsRepository;
  }

  public List<TeamStatisticLog> getLog(String season, String leagueCup, String leagueDivision) {
    return teamStatisticsRepository.getTeamStatisticsLog(season, leagueCup, leagueDivision);
  }

  public List<TeamStatistics> getTeamStatisticHistory(String teamName){
   return teamStatisticsRepository.findTeamStatisticsByTeamName(teamName);

  }
}


