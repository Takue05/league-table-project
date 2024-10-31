package com.takue.leaguetable.service;
import com.takue.leaguetable.model.Match;
import com.takue.leaguetable.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
  private final MatchRepository matchRepository;

  @Autowired
  public MatchService(MatchRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  public Match recordMatch(Match match) {
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
      throw new IllegalArgumentException("A match with the same details already exists.");
    }
    return matchRepository.save(match);
  }


  public List<Match> searchMatches(String homeTeamName, String awayTeamName, LocalDate matchDate, String stadium, String referee, String leagueCup, String season) {
    return matchRepository.searchMatches(homeTeamName, awayTeamName, matchDate, stadium, referee, leagueCup, season);
  }


}

