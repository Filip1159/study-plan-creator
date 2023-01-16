package com.example.studyplanscreator.controller.dto;

public record FoundCourseResponse(
        Long id,
        String name,
        int ECTS,
        int CNPS,
        int ZZU,
        String type
) {
}
