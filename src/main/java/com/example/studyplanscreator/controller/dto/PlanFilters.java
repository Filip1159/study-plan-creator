package com.example.studyplanscreator.controller.dto;

import com.example.studyplanscreator.model.entity.EducationLevel;

public record PlanFilters(
        Long facultyId,
        String field,
        EducationLevel level,
        String academicYear,
        String author
) {}
