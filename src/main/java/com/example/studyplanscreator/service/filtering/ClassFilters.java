package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;

import java.util.List;

public interface ClassFilters {
    boolean matchesFilters(AbstractClass abstractClass, List<FilterCriterion> filters);
}
