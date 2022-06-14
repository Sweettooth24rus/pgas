package com.kkoz.pgas;

import com.kkoz.pgas.entities.dictionaries.Role;
import com.kkoz.pgas.entities.dictionaries.TypeDocument;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.entities.uzer.UzerRole;
import com.kkoz.pgas.repositories.*;
import com.kkoz.pgas.utils.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Profile("default")
@RequiredArgsConstructor
public class dbSeed implements CommandLineRunner {

    private final FileStorageUtil fileStorage;
    private final RepoRole repoRole;
    private final RepoTypeDocument repoTypeDocument;
    private final RepoUzer repoUzer;

    @Override
    @Transactional
    public void run(String... args) {
        log.warn(" <-- Initialization started -->");

        createStorage();

        initRoles();
        initTypeDocuments();

        initUser();

        log.warn(" <-- Initialization ended -->");
    }

    private void createStorage() {
        fileStorage.createStorage();
    }

    private void initRoles() {
        List<Role> roleList = Arrays.asList(
                Role.EXPERT,
                Role.STUDENT
        );
        repoRole.saveAll(roleList);
    }

    private void initTypeDocuments() {
        List<TypeDocument> typeDocumentList = Arrays.asList(
                TypeDocument.APPLICATION_DOCUMENT
        );
        repoTypeDocument.saveAll(typeDocumentList);
    }

    private void initUser() {

        Uzer uzer = new Uzer();
        uzer.setUsername("18731254");
        uzer.setPassword("pwd");
        uzer.setLastName("Козырев");
        uzer.setFirstName("Константин");
        uzer.setPatronymicName("Николаевич");
        uzer.setEmail("kostya_superstar@mail.ru");
        uzer.setUzerGroup("БПИ18-01");
        uzer.setRole(UzerRole.STUDENT);
        uzer.setActive(true);

        repoUzer.save(uzer);

    }
}
