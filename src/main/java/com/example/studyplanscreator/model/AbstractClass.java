package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.LearningEffect;
import com.example.studyplanscreator.model.entity.Semester;
import com.example.studyplanscreator.model.entity.Type;
import com.example.studyplanscreator.model.entity.WayOfCrediting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractClass {
    private Long id;
    private String name;
    private int ECTS;
    private int CNPS;
    private int ZZU;
    private WayOfCrediting wayOfCrediting;
    private Type type;
    private String area;
    private List<LearningEffect> learningEffects;
    private Semester semester;

    public int getPoints(PointType pointType) {
        return switch (pointType) {
            case ECTS -> ECTS;
            case CNPS -> CNPS;
            case ZZU -> ZZU;
        };
    }
}
