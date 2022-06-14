package com.kkoz.pgas.dto.rating;

import com.kkoz.pgas.entities.application.Application;
import lombok.Data;

@Data
public class DtoRatingPagination {

    private Integer number;
    private String fio;
    private String group;
    private Integer score;

    public DtoRatingPagination(Application entity) {
        this.fio = entity.getUzer().getFirstName() + " " + entity.getUzer().getLastName() + " " + entity.getUzer().getPatronymicName();
        this.group = entity.getUzer().getUzerGroup();
        this.score = entity.getSumScore();
    }
}
