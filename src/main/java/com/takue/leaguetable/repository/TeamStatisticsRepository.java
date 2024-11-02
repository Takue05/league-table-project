package com.takue.leaguetable.repository;

import com.takue.leaguetable.model.TeamStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TeamStatisticsRepository extends JpaRepository<TeamStatistics, Long> {


  List<TeamStatistics> findTeamStatisticsByTeamName(String teamName);
  TeamStatistics findTeamStatisticsByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(String teamName, String leagueDivision, String season, String leagueCup);

  @Query("SELECT ROW_NUMBER() OVER(ORDER BY t.teamName) AS position, " +
    "t.teamName, " +
    "t.awayGoals, " +
    "t.homeGoals, " +
    "t.gamesDrawn, " +
    "t.gamesLost, " +
    "t.gamesWon, " +
    "t.goalAgainst, " +
    "t.goalsFor, " +
    "t.goalsDifference, " +
    "t.points " +
    "FROM TeamStatistics t " +
    "WHERE t.season = :season AND t.leagueCup = :leagueCup AND t.leagueDivision = :leagueDivision " +
    "order by t.points desc ")
  List<TeamStatisticLog> getTeamStatisticsLog(@Param("season") String season,
                                                   @Param("leagueCup") String leagueCup,
                                                   @Param("leagueDivision") String leagueDivision);

  @Modifying
  @Transactional
  @Query("UPDATE TeamStatistics t SET " +
    "t.awayGoals = :awayGoals, " +
    "t.homeGoals = :homeGoals, " +
    "t.gamesDrawn = :gamesDrawn, " +
    "t.gamesLost = :gamesLost, " +
    "t.gamesWon = :gamesWon, " +
    "t.goalAgainst = :goalAgainst, " +
    "t.goalsFor = :goalsFor, " +
    "t.goalsDifference = :goalsDifference," +
    "t.points = :points, " +
    "t.gamesPlayed = :gamesPlayed " +
    "WHERE t.season = :season " +
    "AND t.leagueCup = :leagueCup " +
    "AND t.teamName = :teamName " +
    "AND t.leagueDivision = :leagueDivision")
  void updateTeamStatistics(@Param("season") String season,
                            @Param("leagueCup") String leagueCup,
                            @Param("teamName") String teamName,
                            @Param("leagueDivision") String leagueDivision,
                            @Param("awayGoals") int awayGoals,
                            @Param("homeGoals") int homeGoals,
                            @Param("gamesDrawn") int gamesDrawn,
                            @Param("gamesLost") int gamesLost,
                            @Param("gamesWon") int gamesWon,
                            @Param("goalAgainst") int goalAgainst,
                            @Param("goalsFor") int goalsFor,
                            @Param("goalsDifference") int goalsDifference,
                            @Param("points") int points,
                            @Param("gamesPlayed") int gamesPlayed);


  void deleteByTeamNameAndLeagueDivisionAndSeasonAndLeagueCup(String teamName, String leagueDivision, String season, String leagueCup);

}

