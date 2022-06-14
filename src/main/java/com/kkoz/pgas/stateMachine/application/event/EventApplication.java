package com.kkoz.pgas.stateMachine.application.event;

public enum EventApplication {
    NEXT,     // В следующий "положительный" статус
    FOR_REV,  // В статус "На доработку"
    TO_CLOSED  // В статус "На доработку"
}
