package com.takue.leaguetable.repository;

import com.takue.leaguetable.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
  Team findTeamByTeamId(Long teamId);
}
