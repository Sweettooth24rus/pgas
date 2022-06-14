package com.kkoz.pgas.controls.exceptions;

public class CannotShowFileException extends RuntimeException {
    public CannotShowFileException(String file) {
        super("Невозможно отобразить файл [" + file + "]");
    }
}