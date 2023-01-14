package com.example.studyplanscreator.service;

import com.example.studyplanscreator.controller.dto.ReadableNameDto;
import com.example.studyplanscreator.model.entity.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TranslationService {
    private static final Map<CourseType, String> courseTypeTranslations = Map.of(
            CourseType.LECTURE, "Wykład",
            CourseType.EXERCISES, "Ćwiczenia",
            CourseType.LAB, "Laboratorium",
            CourseType.PROJECT, "Projekt",
            CourseType.SEMINARY, "Seminarium"
    );

    private static final Map<WayOfCrediting, String> wayOfCreditingTranslations = Map.of(
            WayOfCrediting.EXAM, "Egzamin",
            WayOfCrediting.PASS, "Zaliczenie"
    );

    private static final Map<ClassCategory, String> classCategoryTranslations = Map.of(
            ClassCategory.COURSE, "Kurs",
            ClassCategory.GROUP, "Grupa kursów",
            ClassCategory.MODULE, "Moduł kursów"
    );

    private static final Map<EducationLevel, String> educationLevelTranslations = Map.of(
            EducationLevel.FIRST, "Studia pierwszego stopnia",
            EducationLevel.SECOND, "Studia drugiego stopnia"
    );

    private static final Map<Type, String> typeTranslations = Map.of(
            Type.K, "Kierunkowy (K)",
            Type.KO, "Kształcenia ogólnego (KO)",
            Type.PD, "Podstawowy (PD)",
            Type.S, "Specjalnościowy (S)"
    );

    public List<String> translate(CourseType[] courseTypes) {
        return Arrays.stream(courseTypes).map(this::translate).toList();
    }

    public List<ReadableNameDto<CourseType>> readableNames(CourseType[] courseTypes) {
        return Arrays.stream(courseTypes)
                .map(courseType -> new ReadableNameDto<>(courseType, translate(courseType)))
                .toList();
    }

    public String translate(CourseType courseType) {
        return courseTypeTranslations.get(courseType);
    }

    public List<String> translate(WayOfCrediting[] waysOfCrediting) {
        return Arrays.stream(waysOfCrediting).map(this::translate).toList();
    }

    public List<ReadableNameDto<WayOfCrediting>> readableNames(WayOfCrediting[] waysOfCrediting) {
        return Arrays.stream(waysOfCrediting)
                .map(wayOfCrediting -> new ReadableNameDto<>(wayOfCrediting, translate(wayOfCrediting)))
                .toList();
    }

    public String translate(WayOfCrediting wayOfCrediting) {
        return wayOfCreditingTranslations.get(wayOfCrediting);
    }

    public List<String> translate(ClassCategory[] classCategories) {
        return Arrays.stream(classCategories).map(this::translate).toList();
    }

    public List<ReadableNameDto<ClassCategory>> readableNames(ClassCategory[] classCategories) {
        return Arrays.stream(classCategories)
                .map(classCategory -> new ReadableNameDto<>(classCategory, translate(classCategory)))
                .toList();
    }

    public String translate(ClassCategory classCategory) {
        return classCategoryTranslations.get(classCategory);
    }

    public List<String> translate(EducationLevel[] educationLevels) {
        return Arrays.stream(educationLevels).map(this::translate).toList();
    }

    public List<ReadableNameDto<EducationLevel>> readableNames(EducationLevel[] educationLevels) {
        return Arrays.stream(educationLevels)
                .map(educationLevel -> new ReadableNameDto<>(educationLevel, translate(educationLevel)))
                .toList();
    }

    public String translate(EducationLevel educationLevel) {
        return educationLevelTranslations.get(educationLevel);
    }

    public List<String> translate(Type[] types) {
        return Arrays.stream(types).map(this::translate).toList();
    }

    public List<ReadableNameDto<Type>> readableNames(Type[] types) {
        return Arrays.stream(types)
                .map(type -> new ReadableNameDto<>(type, translate(type)))
                .toList();
    }

    public String translate(Type type) {
        return typeTranslations.get(type);
    }
}
