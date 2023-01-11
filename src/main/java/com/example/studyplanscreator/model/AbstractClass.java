package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractClass {
    private Long id;
    private String name;
    private Integer ECTS;
    private Integer CNPS;
    private Integer ZZU;
    private WayOfCrediting wayOfCrediting;
    private Type type;
    private String area;
    private ClassCategory category;
    private List<LearningEffect> learningEffects;
    private Long moduleId;
    private Semester semester;

    public int getPoints(PointType pointType) {
        return switch (pointType) {
            case ECTS -> ECTS;
            case CNPS -> CNPS;
            case ZZU -> ZZU;
        };
    }
}
