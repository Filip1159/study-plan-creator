package com.example.studyplanscreator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CourseModule extends AbstractClass {
    private List<Course> courses;
    private CourseGroup courseGroup;
}
