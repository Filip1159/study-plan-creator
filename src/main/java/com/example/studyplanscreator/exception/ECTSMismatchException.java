package com.example.studyplanscreator.exception;

public class ECTSMismatchException extends RuntimeException {
    public ECTSMismatchException() {
        super("Ilość punktów ECTS nie zgadza się z założoną sumą");
    }
}
