package com.example.studyplanscreator.controller.dto;

import com.example.studyplanscreator.model.Course;
import com.example.studyplanscreator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoundCourseResponseMapper {
    private final TranslationService translationService;

    public FoundCourseResponse from(Course course) {
        return new FoundCourseResponse(
                course.getId(),
                course.getName(),
                course.getECTS(),
                course.getCNPS(),
                course.getZZU(),
                translationService.translate(course.getCourseType()));
    }
}
