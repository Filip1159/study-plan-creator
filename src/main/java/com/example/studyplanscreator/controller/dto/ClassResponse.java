package com.example.studyplanscreator.controller.dto;

public record ClassResponse(
        Long id,
        String name,
        int ECTS,
        int CNPS,
        int ZZU,
        String category
) {
}
