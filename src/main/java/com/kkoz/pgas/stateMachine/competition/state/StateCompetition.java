package com.kkoz.pgas.stateMachine.competition.state;

import com.kkoz.pgas.entities.dictionaries.status.StatusCompetition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Статус конкурса для машины-состояний
 * может быть сколько угодно конкурсов в этом статусе PREPARATION и CLOSED но с разными годами
 * в остальных только один конкурс
 */

@Getter
@AllArgsConstructor
public enum StateCompetition {

    DRAFT        ("DRAFT", "Подготовка"),
    SUBMISSION   ("SUBMISSION", "Подача заявок"),
    EVALUATION   ("EVALUATION", "Оценка заявок"),
    ALLOCATION   ("ALLOCATION", "Ожидание документов"),
    CLOSED       ("CLOSED", "Закрыт");

    private final String key;
    private final String value;

}
