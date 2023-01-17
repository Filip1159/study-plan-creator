package com.example.studyplanscreator.controller.dto;

import java.util.List;

public record PlanResponse(
        Long id,
        String facultyName,
        String field,
        String educationLevel,
        String academicYear,
        List<String> authors
) {}
