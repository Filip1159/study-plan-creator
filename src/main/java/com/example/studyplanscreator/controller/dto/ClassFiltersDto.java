package com.example.studyplanscreator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassFiltersDto {
    private Integer ects;
    private Integer cnps;
    private Integer zzu;
    private String category;
    private Integer[] learningEffects;
    private String type;
    private String area;
    private Integer lectureEcts;
    private Integer projectEcts;
    private Integer labEcts;
    private Integer exercisesEcts;
    private Integer seminaryEcts;
    private Integer lectureCnps;
    private Integer projectCnps;
    private Integer labCnps;
    private Integer exercisesCnps;
    private Integer seminaryCnps;
    private Integer lectureZzu;
    private Integer projectZzu;
    private Integer labZzu;
    private Integer exercisesZzu;
    private Integer seminaryZzu;
}
