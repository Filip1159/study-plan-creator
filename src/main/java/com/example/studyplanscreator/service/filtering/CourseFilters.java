package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;
import com.example.studyplanscreator.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseFilters implements ClassFilters {
    @Override
    public boolean matchesFilters(AbstractClass course, List<FilterCriterion> filters) {
        return filters.stream()
                .allMatch(criterion -> matches(criterion, (Course) course));
    }

    private boolean matches(FilterCriterion criterion, Course course) {
        var coursePoints = course.getPoints(criterion.getPointType());
        if (course.getCourseType().equals(criterion.getCourseType()))
            return coursePoints == criterion.getRequiredAmount();
        else return criterion.getRequiredAmount() == 0;
    }
}
