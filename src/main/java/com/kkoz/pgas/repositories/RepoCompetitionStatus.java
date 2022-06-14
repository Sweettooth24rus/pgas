package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.dictionaries.status.StatusApplication;
import com.kkoz.pgas.entities.dictionaries.status.StatusCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCompetitionStatus extends JpaRepository<StatusCompetition, Integer> {
}
