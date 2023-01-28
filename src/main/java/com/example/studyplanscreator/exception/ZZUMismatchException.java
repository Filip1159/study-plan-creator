package com.example.studyplanscreator.exception;

public class ZZUMismatchException extends RuntimeException {
    public ZZUMismatchException() {
        super("Ilość godzin ZZU nie zgadza się z założoną sumą");
    }
}
