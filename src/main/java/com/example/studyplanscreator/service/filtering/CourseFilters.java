package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;
import com.example.studyplanscreator.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseFilters implements ClassFilters {
    @Override
    public boolean matchesFilters(AbstractClass course, List<FilterCriterion> filters) {
        System.out.println(course);
        filters.forEach(System.out::println);
        return filters.stream()
                .allMatch(filter -> course.getPoints(filter.getPointType()) == filter.getRequiredAmount()
                        && filter.getCourseType().equals(((Course) course).getCourseType())
                );

    }
}
