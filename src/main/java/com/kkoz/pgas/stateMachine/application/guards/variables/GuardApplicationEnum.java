package com.kkoz.pgas.stateMachine.application.guards.variables;

import com.kkoz.pgas.stateMachine._state_machine_enum.GuardEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuardApplicationEnum implements GuardEnum {

    DRAFT_TO_IN_PROCESSING          ("DRAFT_TO_IN_PROCESSING", "Результат проверки перехода 'Черновик' -> 'На рассмотрении'"),
    IN_PROCESSING_TO_ACCEPTED       ("IN_PROCESSING_TO_ACCEPTED", "Результат проверки перехода 'На рассмотрении' -> 'Рассмотрена'"),
    IN_PROCESSING_TO_FOR_REVISION   ("IN_PROCESSING_TO_FOR_REVISION", "Результат проверки перехода 'На рассмотрении' -> 'Требует доработок'"),
    FOR_REVISION_TO_IN_PROCESSING   ("FOR_REVISION_TO_IN_PROCESSING", "Результат проверки перехода 'Требует доработок' -> 'На рассмотрении'"),
    ACCEPTED_TO_CLOSED              ("ACCEPTED_TO_CLOSED", "Результат проверки перехода 'На рассмотрении' -> 'Закрыт'");

    private final String key;
    private final String value;

}
