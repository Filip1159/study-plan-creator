package com.example.studyplanscreator.controller.dto;

public record ReadableNameDto<T extends Enum<T>> (
        T value,
        String readableName
) {}
