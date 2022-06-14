package com.kkoz.pgas.stateMachine.competition.guards;

import com.kkoz.pgas.stateMachine._state_machine_enum.GuardEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuardCompetitionEnum implements GuardEnum {

    DRAFT_TO_SUBMISSION              ("DRAFT_TO_SUBMISSION", "Результат проверки перехода 'Подготовка' -> 'Подача заявок'"),
    SUBMISSION_TO_EVALUATION         ("SUBMISSION_TO_EVALUATION", "Результат проверки перехода 'Подача заявок' -> 'Оценка заявок'"),
    EVALUATION_TO_ALLOCATION         ("EVALUATION_TO_ALLOCATION", "Результат проверки перехода 'Оценка заявок' -> 'Ожидание документов'"),
    ALLOCATION_TO_CLOSED             ("ALLOCATION_TO_CLOSED","Результат проверки перехода 'Ожидание документов' -> 'Закрыт'");

    private final String key;
    private final String value;

}
