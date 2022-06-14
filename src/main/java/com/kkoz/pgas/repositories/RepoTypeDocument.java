package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.dictionaries.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoTypeDocument extends JpaRepository<TypeDocument, Integer> {
    TypeDocument findByKey(String key);
}
