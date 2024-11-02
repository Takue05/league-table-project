package com.takue.leaguetable.service;

import com.takue.leaguetable.Utils.MatchOutcome;
import com.takue.leaguetable.model.Match;
import com.takue.leaguetable.model.TeamStatistics;
import com.takue.leaguetable.repository.MatchRepository;
import com.takue.leaguetable.repository.TeamStatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
  private final MatchRepository matchRepository;
  private final TeamStatisticsRepository teamStatisticsRepository;
  private static final Logger log = LoggerFactory.getLogger(MatchService.class);

  @Autowired
  public MatchService(MatchRepository matchRepository, TeamStatisticsRepository teamStatisticsRepository) {
    this.matchRepository = matchRepository;
    this.teamStatisticsRepository = teamStatisticsRepository;

  }

  public void recordMatch(Match match) {
    validateMatch(match);
    Optional<Match> existingMatch = matchRepository.findMatchByAllDetails(
      match.getHomeTeamName(),
      match.getAwayTeamName(),
      match.getMatchDate(),
      match.getAwayTeamScore(),
      match.getHomeTeamScore(),
      match.getStadium(),
      match.getReferee(),
      match.getLeagueCup(),
      match.getSeason()
    );


    if (existingMatch.isPresent()) {
      log.warn("Attempted to record a duplicate match: {}", match);
      throw new IllegalArgumentException("A match with the same details already exists.");
    }
    log.info("Recording new match: {}", match);
    matchRepository.save(match);
    updateTeamStatistics(match);
  }


  public List<Match> searchMatches(String homeTeamName, String awayTeamName, LocalDate matchDate, String stadium, String referee, String leagueCup, String season) {
    return matchRepository.searchMatches(homeTeamName, awayTeamName, matchDate, stadium, referee, leagueCup, season);
  }

  private void validateMatch(Match match) {
    if (match == null) {
      throw new IllegalArgumentException("Match cannot be null");
    }
    if (match.getHomeTeamName() == null || match.getAwayTeamName() == null) {
      throw new IllegalArgumentException("Home team and away team names must be provided.");
    }


  }

  private void updateTeamStatistics(Match match) {
    // Update home team statistics
    TeamStatistics homeTeamStats = teamStatisticsRepository.findTeamStatisticsByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(match.getAwayTeamName(), match.getLeagueCup(), match.getLeagueDivision(), match.getSeason() );
    if (homeTeamStats == null) {
      throw new IllegalArgumentException("The home team is not registered");

    } else {
      int pointsGainedHomeTeam = calculatePoints(match.getHomeTeamScore(), match.getAwayTeamScore());
      MatchOutcome matchOutcomeHomeTeam = determineMatchOutcome(match.getHomeTeamScore(), match.getAwayTeamScore());
      int newGoalForHomeTeam = homeTeamStats.getGoalsFor() + match.getHomeTeamScore();
      int newGoalsAgainstHomeTeam = homeTeamStats.getGoalAgainst() + match.getAwayTeamScore();
      int newGoalDifferenceHomeTeam = homeTeamStats.getGoalAgainst() - match.getAwayTeamScore();
      int newGamesPlayedHomeTeam = homeTeamStats.getGamesPlayed() + 1;
      int newHomeGoalsHomeTeam = homeTeamStats.getHomeGoals() + match.getHomeTeamScore();
      int newHomeTeamPoints = homeTeamStats.getPoints() + pointsGainedHomeTeam;
      int newAwayGoalsHomeTeam = homeTeamStats.getAwayGoals();
      int newGamesWon;
      int newGamesLost;
      int newGamesDrawn;
      if (matchOutcomeHomeTeam.equals(MatchOutcome.WIN)) {
        newGamesWon = (homeTeamStats.getGamesWon() + 1);
      } else {
        newGamesWon = (homeTeamStats.getGamesWon());
      }

      if (matchOutcomeHomeTeam.equals(MatchOutcome.LOSS)) {
        newGamesLost = (homeTeamStats.getGamesLost() + 1);
      } else {
        newGamesLost = (homeTeamStats.getGamesLost());
      }

      if (matchOutcomeHomeTeam.equals(MatchOutcome.DRAW)) {
        newGamesDrawn = (homeTeamStats.getGamesDrawn() + 1);
      } else newGamesDrawn = (homeTeamStats.getGamesDrawn());

      teamStatisticsRepository.updateTeamStatistics(
        match.getSeason(),
        match.getLeagueCup(),
        match.getHomeTeamName(),
        match.getLeagueDivision(),
        newAwayGoalsHomeTeam,  // Assuming these values are already set properly
        newHomeGoalsHomeTeam,
        newGamesDrawn,
        newGamesLost,
        newGamesWon,
        newGoalsAgainstHomeTeam,
        newGoalForHomeTeam,
        newGoalDifferenceHomeTeam,
        newHomeTeamPoints,
        newGamesPlayedHomeTeam
      );

      log.info("Updated statistics for home team: {}", homeTeamStats);
    }


    // Update away team statistics
    TeamStatistics awayTeamStats = teamStatisticsRepository.findTeamStatisticsByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(match.getAwayTeamName(), match.getLeagueCup(), match.getLeagueDivision(), match.getSeason() );
    if (awayTeamStats == null) {
      throw new IllegalArgumentException("The away team is not registered");

    } else {
      int pointsGainedAwayTeam = calculatePoints(match.getAwayTeamScore(), match.getHomeTeamScore());
      MatchOutcome matchOutcomeAwayTeam = determineMatchOutcome(match.getAwayTeamScore(), match.getHomeTeamScore());
      int newGoalForAwayTeam = awayTeamStats.getGoalsFor() + match.getAwayTeamScore();
      int newGoalsAgainstAwayTeam = awayTeamStats.getGoalAgainst() + match.getHomeTeamScore();
      int newGoalDifferenceAwayTeam = awayTeamStats.getGoalsFor() - awayTeamStats.getGoalAgainst();
      int newGamesPlayedAwayTeam = awayTeamStats.getGamesPlayed() + 1;
      int newAwayGoalsAwayTeam = awayTeamStats.getAwayGoals() + match.getAwayTeamScore();
      int newAwayTeamPoints = awayTeamStats.getPoints() + pointsGainedAwayTeam;
      int newGamesWonAwayTeam;
      int newGamesLostAwayTeam;
      int newHomeGoalsAwayTeam = awayTeamStats.getHomeGoals();
      int newGamesDrawnAwayTeam;
      if (matchOutcomeAwayTeam.equals(MatchOutcome.WIN)) {
        newGamesWonAwayTeam = (awayTeamStats.getGamesWon() + 1);
      } else {
        newGamesWonAwayTeam = (awayTeamStats.getGamesWon());
      }

      if (matchOutcomeAwayTeam.equals(MatchOutcome.LOSS)) {
        newGamesLostAwayTeam = (awayTeamStats.getGamesLost() + 1);
      } else {
        newGamesLostAwayTeam = (awayTeamStats.getGamesLost());
      }

      if (matchOutcomeAwayTeam.equals(MatchOutcome.DRAW)) {
        newGamesDrawnAwayTeam = (awayTeamStats.getGamesDrawn() + 1);
      } else
        newGamesDrawnAwayTeam = (awayTeamStats.getGamesDrawn());


      teamStatisticsRepository.updateTeamStatistics(
        match.getSeason(),
        match.getLeagueCup(),
        match.getAwayTeamName(),
        match.getLeagueDivision(),
        newAwayGoalsAwayTeam,  // Assuming these values are already set properly
        newHomeGoalsAwayTeam,   // Assuming these values are already set properly
        newGamesDrawnAwayTeam,
        newGamesLostAwayTeam,
        newGamesWonAwayTeam,
        newGoalsAgainstAwayTeam,
        newGoalForAwayTeam,
        newGoalDifferenceAwayTeam,
        newAwayTeamPoints,
        newGamesPlayedAwayTeam

      );
      log.info("Updated statistics for away team: {}", awayTeamStats);
    }
  }


  private int calculatePoints(int teamGoals, int opponentGoals) {
    if (teamGoals > opponentGoals) {
      return 3; // Win
    } else if (teamGoals == opponentGoals) {
      return 1; // Draw
    } else {
      return 0; // Loss
    }
  }

  public MatchOutcome determineMatchOutcome(int homeTeamScore, int awayTeamScore) {
    if (homeTeamScore > awayTeamScore) {
      return MatchOutcome.WIN; // Home team wins
    } else if (homeTeamScore < awayTeamScore) {
      return MatchOutcome.LOSS; // Home team loses
    } else {
      return MatchOutcome.DRAW; // Match is a draw
    }
  }


}

