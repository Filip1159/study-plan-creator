package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;
import com.example.studyplanscreator.model.CourseModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseModuleFilters implements ClassFilters {
    @Override
    public boolean matchesFilters(AbstractClass module, List<FilterCriterion> filters) {
        return filters.stream()
                .allMatch(filter -> ((CourseModule<?>) module).getPoints(filter.getCourseType(), filter.getPointType())
                        == filter.getRequiredAmount());
    }
}
