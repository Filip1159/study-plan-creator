package com.example.studyplanscreator.model;

import com.example.studyplanscreator.model.entity.CourseType;

public interface ModuleMember {
    int getPoints(CourseType courseType, PointType pointType);
}
