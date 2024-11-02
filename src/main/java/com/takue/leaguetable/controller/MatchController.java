package com.takue.leaguetable.controller;

import com.takue.leaguetable.model.Match;
import com.takue.leaguetable.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

  private final MatchService matchService;

  @Autowired
  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

    @PostMapping("/record")
      public ResponseEntity<String> recordMarch(@RequestBody Match match){

      try {
        matchService.recordMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body("Match recorded successfully.");
      } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }

    }

  @GetMapping("/search")
  public ResponseEntity<List<Match>> searchMatches(
    @RequestParam(required = false) String homeTeamName,
    @RequestParam(required = false) String awayTeamName,
    @RequestParam(required = false) LocalDate matchDate,
    @RequestParam(required = false) String stadium,
    @RequestParam(required = false) String referee,
    @RequestParam(required = false) String leagueCup,
    @RequestParam(required = false) String season){

    List<Match> matches = matchService.searchMatches(homeTeamName, awayTeamName, matchDate, stadium, referee, season, leagueCup  );
    return ResponseEntity.ok(matches);

  }
}

