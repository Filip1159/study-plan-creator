package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;
import com.example.studyplanscreator.model.Course;
import com.example.studyplanscreator.model.CourseGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FiltersFactory {
    private final CourseFilters courseFilters;
    private final CourseGroupFilters courseGroupFilters;
    private final CourseModuleFilters courseModuleFilters;

    public ClassFilters getFor(AbstractClass abstractClass) {
        if (abstractClass instanceof Course) return courseFilters;
        else if (abstractClass instanceof CourseGroup) return courseGroupFilters;
        else return courseModuleFilters;
    }
}
