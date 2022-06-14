package com.kkoz.pgas.dto.competition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkoz.pgas.dto.application.DtoApplicationFile;
import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoCompetition {

    private Integer id;
    private Integer year;
    private Integer term;
    private String direction;
    private String status;
    private Integer count;

    public DtoCompetition(Competition entity) {
        this.id = entity.getId();
        this.year = entity.getYear();
        this.term = entity.getTerm();
        this.direction = entity.getDirection().getKey();
        this.status = entity.getStatus().getKey();
        this.count = entity.getCount();
    }
}
