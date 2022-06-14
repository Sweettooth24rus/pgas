package com.kkoz.pgas.dto.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkoz.pgas.dto.file.DtoFileUpload;
import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoApplication {

    private Integer id;
    private Direction direction;
    private Integer competitionId;
    private Integer uzerId;
    private String status;
    private List<DtoApplicationFile> applicationFileList;
    private Integer year;
    private Integer term;
    private String comment;
    private Integer sumScore;

    public DtoApplication(Application entity) {
        this.id = entity.getId();
        this.direction = entity.getCompetition().getDirection();
        this.competitionId = entity.getCompetition().getId();
        this.uzerId = entity.getUzer().getId();
        this.status = entity.getStatus().getValue();
        this.applicationFileList = entity.getApplicationFileList()
                .stream().map(DtoApplicationFile::new).collect(Collectors.toList());
        this.year = entity.getCompetition().getYear();
        this.term = entity.getCompetition().getTerm();
        this.sumScore = entity.getSumScore();;
    }
}
