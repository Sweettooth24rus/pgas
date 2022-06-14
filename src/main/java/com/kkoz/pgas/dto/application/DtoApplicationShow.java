package com.kkoz.pgas.dto.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class DtoApplicationShow {

    private Integer id;
    private String status;
    private String direction;
    private Integer competitionId;
    private Integer uzerId;
    private List<DtoApplicationFile> applicationFileList;
    private Integer sumScore;

    private String comment;

    public DtoApplicationShow(Application entity) {
        this.id = entity.getId();
        this.status = entity.getStatus().getKey();
        this.direction = entity.getCompetition().getDirection().getKey();
        this.competitionId = entity.getCompetition().getId();
        this.uzerId = entity.getUzer().getId();
        this.applicationFileList = entity.getApplicationFileList().stream()
                .map(DtoApplicationFile::new).collect(Collectors.toList());
        comment = null;
        if (entity.getComment() != null) {
            this.comment = entity.getComment();
        }
        this.sumScore = entity.getSumScore();
    }
}
