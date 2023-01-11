package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Course extends AbstractClass {
    private CourseType courseType;
    private CourseGroup group;
    private CourseModule module;
}
