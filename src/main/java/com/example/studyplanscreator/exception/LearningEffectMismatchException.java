package com.example.studyplanscreator.exception;

public class LearningEffectMismatchException extends RuntimeException {
    public LearningEffectMismatchException() {
        super("Efekty uczenia się nie pokrywają się");
    }
}
