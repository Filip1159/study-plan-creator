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
public class CourseModule<T extends ModuleMember> extends AbstractClass {
    private List<T> members;

    @Builder
    public CourseModule(Long id, String name, int ECTS, int CNPS, int ZZU, WayOfCrediting wayOfCrediting, Type type,
                       String area, List<LearningEffect> learningEffects, Semester semester,
                       List<T> members) {
        super(id, name, ECTS, CNPS, ZZU, wayOfCrediting, type, area, learningEffects, semester);
        this.members = members;
    }
}
