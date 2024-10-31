package com.takue.leaguetable.repository;

import com.takue.leaguetable.model.TeamStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchStatisticsRepository extends JpaRepository<TeamStatistics, Long > {


  @Query("SELECT ROW_NUMBER() OVER(ORDER BY t.teamName) AS position, " +
    "t.teamName, " +
    "t.awayGoals, " +
    "t.homeGoals, " +
    "t.gamesDrawn AS D, " +
    "t.gamesLost AS L, " +
    "t.gamesWon AS W, " +
    "t.goalAgainst AS AG, " +
    "t.goalsFor AS GF, " +
    "t.goalsDifference AS GD " +
    "FROM TeamStatistics t " +
    "WHERE t.season = :season AND t.league = :league AND t.leagueDivision = :leagueDivision")
  List<TeamStatistics> getTeamStatistics(@Param("season") String season,
                                         @Param("league") String league,
                                         @Param("leagueDivision") String leagueDivision);

  @Modifying
  @Query("UPDATE TeamStatistics t SET " +
    "t.awayGoals = :awayGoals, " +
    "t.homeGoals = :homeGoals, " +
    "t.gamesDrawn = :gamesDrawn, " +
    "t.gamesLost = :gamesLost, " +
    "t.gamesWon = :gamesWon, " +
    "t.goalAgainst = :goalAgainst, " +
    "t.goalsFor = :goalsFor, " +
    "t.goalsDifference = :goalsDifference " +
    "WHERE t.season = :season " +
    "AND t.league = :league " +
    "AND t.teamName = :teamName " +
    "AND t.leagueDivision = :leagueDivision")
  void updateTeamStatistics(@Param("season") String season,
                            @Param("league") String league,
                            @Param("teamName") String teamName,
                            @Param("leagueDivision") String leagueDivision,
                            @Param("awayGoals") int awayGoals,
                            @Param("homeGoals") int homeGoals,
                            @Param("gamesDrawn") int gamesDrawn,
                            @Param("gamesLost") int gamesLost,
                            @Param("gamesWon") int gamesWon,
                            @Param("goalAgainst") int goalAgainst,
                            @Param("goalsFor") int goalsFor,
                            @Param("goalsDifference") int goalsDifference);


}
