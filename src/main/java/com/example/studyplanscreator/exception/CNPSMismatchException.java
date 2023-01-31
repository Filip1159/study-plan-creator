package com.example.studyplanscreator.exception;

public class CNPSMismatchException extends RuntimeException {
    public CNPSMismatchException() {
        super("Ilość punktów CNPS nie zgadza się z założoną sumą");
    }
}
