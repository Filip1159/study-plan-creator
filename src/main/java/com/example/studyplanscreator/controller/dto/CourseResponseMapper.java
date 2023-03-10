package com.example.studyplanscreator.controller.dto;

import com.example.studyplanscreator.model.Course;
import com.example.studyplanscreator.model.CourseGroup;
import com.example.studyplanscreator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseResponseMapper {
    private final TranslationService translationService;

    public CourseResponse from(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getECTS(),
                course.getCNPS(),
                course.getZZU(),
                translationService.translate(course.getCourseType()));
    }

    public CourseResponse from(CourseGroup courseGroup) {
        return new CourseResponse(
                courseGroup.getId(),
                courseGroup.getName(),
                courseGroup.getECTS(),
                courseGroup.getCNPS(),
                courseGroup.getZZU(),
                null);
    }
}
