package com.kkoz.pgas.services;

import com.kkoz.pgas.entities.dictionaries.EntityDictionary;
import com.kkoz.pgas.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceDictionaries {

    private final RepoRole repoRole;
    private final RepoDirection repoDirection;
    private final RepoApplicationStatus repoApplicationStatus;
    private final RepoCompetitionStatus repoCompetitionStatus;

    public Map<String, List<EntityDictionary>> getAllItems() {

        log.info(">> getting all dictionaries");
        Map<String, List<EntityDictionary>> dictionaries = new HashMap<>();

        log.debug(" _. getting dictionary Role");
        List<EntityDictionary> dicRole = new ArrayList<>(repoRole.findAll());
        log.trace("  x. dictionary Role:\t{}", dicRole);
        dictionaries.put("UserRole", dicRole);

        log.debug(" _. getting dictionary Direction");
        List<EntityDictionary> dicDirection = new ArrayList<>(repoDirection.findAll());
        log.trace("  x. dictionary Direction:\t{}", dicDirection);
        dictionaries.put("Direction", dicDirection);

        log.debug(" _. getting dictionary ApplicationStatus");
        List<EntityDictionary> dicApplicationStatus = new ArrayList<>(repoApplicationStatus.findAll());
        log.trace("  x. dictionary ApplicationStatus:\t{}", dicApplicationStatus);
        dictionaries.put("ApplicationStatus", dicApplicationStatus);

        log.debug(" _. getting dictionary CompetitionStatus");
        List<EntityDictionary> dicCompetitionStatus = new ArrayList<>(repoCompetitionStatus.findAll());
        log.trace("  x. dictionary CompetitionStatus:\t{}", dicCompetitionStatus);
        dictionaries.put("CompetitionStatus", dicCompetitionStatus);

        return dictionaries;
    }
}
