package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.CourseModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseModuleFilters implements ClassFilters<CourseModule> {
    @Override
    public boolean matchesFilters(CourseModule module, List<FilterCriterion> filters) {
        return false;  // TODO
    }
}
