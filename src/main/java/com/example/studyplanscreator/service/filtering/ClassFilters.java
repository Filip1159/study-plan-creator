package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.model.AbstractClass;

import java.util.List;

public interface ClassFilters<T extends AbstractClass> {
    boolean matchesFilters(T abstractClass, List<FilterCriterion> filters);
}
