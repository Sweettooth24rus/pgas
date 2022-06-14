package com.kkoz.pgas.repositories;

import com.kkoz.pgas.entities.file.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepoFileUpload extends JpaRepository<FileUpload, Integer> {

    Optional<FileUpload> findByUuid(UUID uuid);

    List<FileUpload> findAll();

}
