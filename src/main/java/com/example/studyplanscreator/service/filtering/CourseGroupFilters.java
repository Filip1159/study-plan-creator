package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.CourseGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseGroupFilters implements ClassFilters<CourseGroup> {
    public boolean matchesFilters(CourseGroup courseGroup, List<FilterCriterion> filters) {
        return filters.stream()
                .allMatch(filter -> courseGroup.getPoints(filter.getCourseType(), filter.getPointType())
                        == filter.getRequiredAmount());
    }
}
