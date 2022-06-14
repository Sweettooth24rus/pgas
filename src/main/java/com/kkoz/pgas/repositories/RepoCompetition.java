package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCompetition extends JpaRepository<Competition, Integer>, JpaSpecificationExecutor<Competition> {
}
