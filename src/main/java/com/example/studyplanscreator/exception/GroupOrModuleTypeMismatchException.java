package com.example.studyplanscreator.exception;

public class GroupOrModuleTypeMismatchException extends RuntimeException {
    public GroupOrModuleTypeMismatchException() {
        super("Rodzaj zajęć co najmniej jednego z elementów kursu lub grupy się nie zgadza");
    }
}
