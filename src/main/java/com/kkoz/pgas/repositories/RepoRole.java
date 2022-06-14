package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.dictionaries.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRole extends JpaRepository<Role, Integer> {
}
