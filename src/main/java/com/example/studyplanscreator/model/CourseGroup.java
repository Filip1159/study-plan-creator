package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CourseGroup extends AbstractClass {
    private List<Course> courses;

    public int getPoints(CourseType courseType, PointType pointType) {
        return courses.stream()
                .filter(course -> course.getCourseType().equals(courseType))
                .reduce(0, (accumulator, course) -> accumulator + course.getPoints(pointType), Integer::sum);
    }
}
