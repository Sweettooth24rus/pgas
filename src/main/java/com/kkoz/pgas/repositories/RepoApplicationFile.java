package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.application.ApplicationFile;
import com.kkoz.pgas.entities.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoApplicationFile extends JpaRepository<ApplicationFile, Integer> {
}
