package com.kkoz.pgas.services;

import com.kkoz.pgas.controls.exceptions.MismatchException;
import com.kkoz.pgas.controls.exceptions.UniqueUzerException;
import com.kkoz.pgas.dto.application.DtoApplication;
import com.kkoz.pgas.dto.application.DtoApplicationFile;
import com.kkoz.pgas.dto.application.DtoApplicationPagination;
import com.kkoz.pgas.dto.file.DtoFileUpload;
import com.kkoz.pgas.dto.rating.DtoRatingPagination;
import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.application.ApplicationFile;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.entities.dictionaries.TypeDocument;
import com.kkoz.pgas.entities.file.FileUpload;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.repositories.RepoApplication;
import com.kkoz.pgas.repositories.RepoCompetition;
import com.kkoz.pgas.repositories.RepoUzer;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceApplication {
    private final RepoApplication repoApplication;
    private final RepoCompetition repoCompetition;
    private final RepoUzer repoUzer;
    private final ServiceUzer serviceUzer;

    public List<DtoApplicationPagination> getList(String direction) {

        List<Application> recipePage = repoApplication.findAll(Direction.getGroupFromKey(direction), serviceUzer.getCurrentUzer().getId());

        return recipePage.stream()
                .map(DtoApplicationPagination::new)
                .collect(Collectors.toList());
    }

    public List<DtoRatingPagination> getRating(String direction) {

        List<Application> recipePage = repoApplication.findByDirection(Direction.getGroupFromKey(direction));
        List<DtoRatingPagination> dtoRatingPaginations = new ArrayList<>();
        Integer a = 1;

        for (Application application:
             recipePage) {
            DtoRatingPagination dtoRatingPagination = new DtoRatingPagination(application);
            dtoRatingPagination.setNumber(a++);
            dtoRatingPaginations.add(dtoRatingPagination);
        }

        return dtoRatingPaginations;
    }

    public Application getById(Integer id) {
        return repoApplication.getById(id);
    }

    public String deleteApplication(Integer id) {
        repoApplication.deleteById(id);
        return "deleted witch id " + id;
    }

    public Application createApplication(DtoApplication dtoApplication) {
        Application application = new Application();
        return this.saveApplication(application, dtoApplication);
    }

    public Application updateApplication(Integer id, DtoApplication dtoRecipe) {
        if (!id.equals(dtoRecipe.getId())) {
            throw new MismatchException(
                    "Provided id [" + id + "] isn't equal to provided DTO id [" + dtoRecipe.getId() + "]"
            );
        }

        Application recipe = repoApplication.getById(id);

        return this.saveApplication(recipe, dtoRecipe);
    }

    private Application saveApplication(Application recipe, DtoApplication dtoRecipe) {

        recipe.setComment(dtoRecipe.getComment());
        recipe.setCompetition(repoCompetition.getById(dtoRecipe.getCompetitionId()));
        recipe.setUzer(repoUzer.getById(dtoRecipe.getUzerId()));
        recipe.setApplicationFileList(dto2AppFile(dtoRecipe.getApplicationFileList()));

        Application savedRecipe = null;

        try {
            savedRecipe = repoApplication.save(recipe);
        } catch (DataIntegrityViolationException e) {
            ConstraintViolationException constraintViolation = (ConstraintViolationException) e.getCause();
            throw new UniqueUzerException(constraintViolation.getConstraintName());
        }

        return savedRecipe;
    }

    private List<ApplicationFile> dto2AppFile(List<DtoApplicationFile> dtoApplicationFiles) {
        List<ApplicationFile> appFiles = new ArrayList<>();

        for (DtoApplicationFile dtoapplicationFile:
             dtoApplicationFiles) {
            ApplicationFile appFile = new ApplicationFile();
            appFile.setId(dtoapplicationFile.getId());
            appFile.setDescription(dtoapplicationFile.getDescription());
            appFile.setFile(dtoDocs2AppFiles(dtoapplicationFile.getFile()));
            appFiles.add(appFile);
        }

        return appFiles;
    }

    private FileUpload dtoDocs2AppFiles(DtoFileUpload dtoFile) {

            FileUpload appFile = new FileUpload();
            appFile.setId(dtoFile.getId());
            appFile.setCreated(dtoFile.getCreated());
            appFile.setMimeType(dtoFile.getMimeType());
            appFile.setOriginalFileName(dtoFile.getOriginalFileName());
            appFile.setUuid(dtoFile.getUuid());
            appFile.setSize(dtoFile.getSize());
            appFile.setStorePath(dtoFile.getStorePath());
            appFile.setTypeDocument(TypeDocument.APPLICATION_DOCUMENT);

        return appFile;
    }
}
