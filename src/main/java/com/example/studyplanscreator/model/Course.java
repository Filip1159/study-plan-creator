package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Course extends AbstractClass implements ModuleMember {
    private CourseType courseType;

    @Builder
    public Course(Long id, String name, int ECTS, int CNPS, int ZZU, WayOfCrediting wayOfCrediting, Type type,
                  String area, List<LearningEffect> learningEffects, Semester semester,
                  CourseType courseType) {
        super(id, name, ECTS, CNPS, ZZU, wayOfCrediting, type, area, learningEffects, semester);
        this.courseType = courseType;
    }
}
