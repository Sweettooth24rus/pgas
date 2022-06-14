package com.kkoz.pgas.entities.dictionaries.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkoz.pgas.entities.dictionaries.EntityDictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * класс для отображения статусов конкурса на фронте
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatusCompetition extends EntityDictionary implements Serializable {

    public static final StatusCompetition DRAFT      = new StatusCompetition(1 ,"PREPARATION", "Подготовка");
    public static final StatusCompetition SUBMISSION = new StatusCompetition(2, "SUBMISSION", "Подача заявок");
    public static final StatusCompetition EVALUATION = new StatusCompetition(3,"EVALUATION", "Оценка заявок");
    public static final StatusCompetition ALLOCATION = new StatusCompetition(4, "ALLOCATION", "Ожидание документов");
    public static final StatusCompetition CLOSED     = new StatusCompetition(5, "CLOSED", "Закрыт");

    @Id
    @JsonIgnore
    private Integer id;
    private String key;
    private String value;
}
