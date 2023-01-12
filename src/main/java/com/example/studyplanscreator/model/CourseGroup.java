package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseGroup extends AbstractClass implements ModuleMember {
    private List<Course> courses;

    public int getPoints(CourseType courseType, PointType pointType) {
        return courses.stream()
                .filter(course -> course.getCourseType().equals(courseType))
                .reduce(0, (accumulator, course) -> accumulator + course.getPoints(pointType), Integer::sum);
    }

    @Builder
    public CourseGroup(Long id, String name, int ECTS, int CNPS, int ZZU, WayOfCrediting wayOfCrediting, Type type,
                       String area, List<LearningEffect> learningEffects,  Semester semester,
                       List<Course> courses) {
        super(id, name, ECTS, CNPS, ZZU, wayOfCrediting, type, area, learningEffects, semester);
        this.courses = courses;
    }
}
