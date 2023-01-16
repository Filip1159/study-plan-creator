package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.PointType;
import com.example.studyplanscreator.model.entity.CourseType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterCriterion {
    private PointType pointType;
    private CourseType courseType;
    private int requiredAmount;
}
