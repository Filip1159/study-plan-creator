package com.example.studyplanscreator.controller.dto;

import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanResponseMapper {
    private final TranslationService translationService;

    public PlanResponse from(Plan plan) {
        return new PlanResponse(
                plan.getId(),
                plan.getFaculty().getName(),
                plan.getField(),
                translationService.translate(plan.getLevel()),
                plan.getAcademicYear(),
                plan.getAuthors().stream()
                        .map(author -> author.getName() + " " + author.getSurname())
                        .toList());
    }
}
