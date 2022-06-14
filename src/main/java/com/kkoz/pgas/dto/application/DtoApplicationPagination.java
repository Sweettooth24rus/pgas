package com.kkoz.pgas.dto.application;

import com.kkoz.pgas.dto.file.DtoFileUpload;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import lombok.Data;

@Data
public class DtoApplicationPagination {

    private Integer id;
    private Integer year;
    private Integer term;
    private String status;

    public DtoApplicationPagination(Application entity) {
        this.id = entity.getId();
        this.year = entity.getCompetition().getYear();
        this.term = entity.getCompetition().getTerm();
        this.status = entity.getStatus().getKey();
    }
}
