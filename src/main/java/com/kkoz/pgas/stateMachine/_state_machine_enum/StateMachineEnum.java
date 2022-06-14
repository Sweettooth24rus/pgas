package com.kkoz.pgas.stateMachine._state_machine_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateMachineEnum {

    EXCEPTION         ("EXCEPTION", "Для проверки наличия исключения в контексте Машины состояний"),
    EXCEPTION_MESSAGE ("EXCEPTION_MESSAGE", "Текст исключения");

    private final String key;
    private final String value;

}
