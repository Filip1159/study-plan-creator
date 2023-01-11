package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseFilters implements ClassFilters<Course> {
    @Override
    public boolean matchesFilters(Course course, List<FilterCriterion> filters) {
        return filters.stream()
                .allMatch(filter -> course.getPoints(filter.getPointType()) == filter.getRequiredAmount()
                        || !filter.getCourseType().equals(course.getCourseType())
                );

    }
}
