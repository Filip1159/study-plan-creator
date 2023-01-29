package com.example.studyplanscreator.exception;

public class GroupOrModuleEmptyException extends RuntimeException {
    public GroupOrModuleEmptyException() {
        super("Należy dodać co najmniej jeden kurs");
    }
}
