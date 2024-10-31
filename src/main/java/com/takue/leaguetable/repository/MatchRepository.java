package com.takue.leaguetable.repository;
import com.takue.leaguetable.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
  @Query("SELECT m FROM Match m WHERE "
    + "(:homeTeamName IS NULL OR m.homeTeamName = :homeTeamName) AND "
    + "(:awayTeamName IS NULL OR m.awayTeamName = :awayTeamName) AND "
    + "(:matchDate IS NULL OR m.matchDate = :matchDate) AND "
    + "(:stadium IS NULL OR m.stadium = :stadium) AND "
    + "(:referee IS NULL OR m.referee = :referee) AND "
    + "(:leagueCup IS NULL OR m.leagueCup = :leagueCup) AND "
    + "(:season IS NULL OR m.season = :season)")
  List<Match> searchMatches(@Param("homeTeamName") String homeTeamName,
                            @Param("awayTeamName") String awayTeamName,
                            @Param("matchDate") LocalDate matchDate,
                            @Param("stadium") String stadium,
                            @Param("referee") String referee,
                            @Param("leagueCup") String leagueCup,
                            @Param("season") String season);


    @Query("SELECT m FROM Match m WHERE "
      + "m.homeTeamName = :homeTeamName AND "
      + "m.awayTeamName = :awayTeamName AND "
      + "m.matchDate = :matchDate AND "
      + "m.awayTeamScore = :awayTeamScore AND "
      + "m.homeTeamScore = :homeTeamScore AND "
      + "m.stadium = :stadium AND "
      + "m.referee = :referee AND "
      + "m.leagueCup = :leagueCup AND "
      + "m.season = :season")
    Optional<Match> findMatchByAllDetails(@Param("homeTeamName") String homeTeamName,
                                          @Param("awayTeamName") String awayTeamName,
                                          @Param("matchDate") LocalDate matchDate,
                                          @Param("awayTeamScore") int awayTeamScore,
                                          @Param("homeTeamScore") int homeTeamScore,
                                          @Param("stadium") String stadium,
                                          @Param("referee") String referee,
                                          @Param("leagueCup") String leagueCup,
                                          @Param("season") String season);
  }






