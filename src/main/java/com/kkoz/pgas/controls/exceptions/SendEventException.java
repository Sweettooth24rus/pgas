package com.kkoz.pgas.controls.exceptions;

public class SendEventException extends RuntimeException {
    public SendEventException(String message) {
        super(message);
    }

    public SendEventException(String event, String message) {
        super("Переход - " + event + "между статусами не выполнен. " + message);
    }
}
