package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.dictionaries.DirectionDictionary;
import com.kkoz.pgas.entities.dictionaries.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDirection extends JpaRepository<DirectionDictionary, Integer> {
}
