package com.kkoz.pgas.stateMachine.application.state;

import com.kkoz.pgas.entities.dictionaries.status.StatusApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * состояния заявки для машины-состояний
 */

@AllArgsConstructor
@Getter
public enum StateApplication {

    DRAFT("DRAFT", "Черновик"), // черновик
    IN_PROCESSING("IN_PROCESSING", "На рассмотрении"), //На рассмотрении
    FOR_REVISION("FOR_REVISION", "Требует доработок"), //Требует доработок
    ACCEPTED("ACCEPTED", "Рассмотрена"), //Рассмотрена
    CLOSED("CLOSED", "Закрыт");

    private String key;
    private String value;

}
