package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.entities.uzer.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoApplication extends JpaRepository<Application, Integer>, JpaSpecificationExecutor<Application> {
    List<Application> findAllByCompetition(Competition competition);
    @Query(value = "SELECT * FROM Application a WHERE a.competition.direction = :direction AND a.uzer.id = :uzerId", nativeQuery = true)
    List<Application> findAll(Direction direction, Integer uzerId);
    @Query(value = "SELECT * FROM Application a WHERE a.competition.direction = :direction ORDER BY sumScore DESC", nativeQuery = true)
    List<Application> findByDirection(Direction direction);
}
