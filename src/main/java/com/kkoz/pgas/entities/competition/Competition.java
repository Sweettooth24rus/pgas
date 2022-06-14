package com.kkoz.pgas.entities.competition;

import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.dictionaries.status.StatusCompetition;
import com.kkoz.pgas.entities.meta.MetaEntityInteger;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Конкурс
 */
@Entity
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"year"})})
public class Competition extends MetaEntityInteger {
    public static final String WITH_CIP = "WITH_CIP";

    /**
     * Год конкурса
     */
    private Integer year;

    private Integer term;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    /**
     * Статус конкурса
     */
    @Enumerated(EnumType.STRING)
    private StateCompetition status;

    private Integer count;

}
