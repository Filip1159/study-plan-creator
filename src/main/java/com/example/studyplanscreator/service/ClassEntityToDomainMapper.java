package com.example.studyplanscreator.service;

import com.example.studyplanscreator.model.*;
import com.example.studyplanscreator.model.entity.ClassEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.COURSE;

@Component
@RequiredArgsConstructor
public class ClassEntityToDomainMapper {

    public AbstractClass toDomain(ClassEntity entity) {
        return switch (entity.getCategory()) {
            case COURSE -> courseFromEntity(entity);
            case GROUP -> groupFromEntity(entity);
            case MODULE -> moduleFromEntity(entity);
        };
    }

    public Course courseFromEntity(ClassEntity entity) {
        return Course.builder()
                .id(entity.getId())
                .name(entity.getName())
                .area(entity.getArea())
                .CNPS(entity.getCNPS())
                .ECTS(entity.getECTS())
                .ZZU(entity.getZZU())
                .type(entity.getType())
                .learningEffects(List.copyOf(entity.getLearningEffects()))
                .semester(entity.getSemester())
                .wayOfCrediting(entity.getWayOfCrediting())
                .courseType(entity.getCourseType())
                .build();
    }

    @SuppressWarnings("unchecked")
    public <T extends ModuleMember> CourseModule<T> moduleFromEntity(ClassEntity entity) {
        var members = new ArrayList<T>();
        entity.getCoursesInModule().forEach(member -> {
            if (member.getCategory().equals(COURSE))
                members.add((T) courseFromEntity(member));
            else
                members.add((T) groupFromEntity(member));
        });
        return CourseModule.<T>builder()
                .id(entity.getId())
                .name(entity.getName())
                .area(entity.getArea())
                .CNPS(entity.getCNPS())
                .ECTS(entity.getECTS())
                .ZZU(entity.getZZU())
                .type(entity.getType())
                .learningEffects(List.copyOf(entity.getLearningEffects()))
                .semester(entity.getSemester())
                .wayOfCrediting(entity.getWayOfCrediting())
                .members(members)
                .build();
    }

    public CourseGroup groupFromEntity(ClassEntity entity) {
        return CourseGroup.builder()
                .id(entity.getId())
                .name(entity.getName())
                .area(entity.getArea())
                .CNPS(entity.getCNPS())
                .ECTS(entity.getECTS())
                .ZZU(entity.getZZU())
                .type(entity.getType())
                .learningEffects(List.copyOf(entity.getLearningEffects()))
                .semester(entity.getSemester())
                .wayOfCrediting(entity.getWayOfCrediting())
                .courses(entity.getCoursesInGroup().stream()
                        .map(this::courseFromEntity)
                        .toList())
                .build();
    }
}
