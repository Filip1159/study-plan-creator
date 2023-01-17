package com.example.studyplanscreator.controller.dto;

public record CourseResponse(
        Long id,
        String name,
        int ECTS,
        int CNPS,
        int ZZU,
        String courseType
) {
}
