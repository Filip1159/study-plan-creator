package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseModuleFilters implements ClassFilters {
    @Override
    public boolean matchesFilters(AbstractClass module, List<FilterCriterion> filters) {
        return false;  // TODO
    }
}
