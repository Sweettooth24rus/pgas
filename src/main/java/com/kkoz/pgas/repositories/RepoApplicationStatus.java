package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.dictionaries.status.StatusApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoApplicationStatus extends JpaRepository<StatusApplication, Integer> {
}
