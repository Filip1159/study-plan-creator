package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.PointType;
import com.example.studyplanscreator.model.entity.CourseType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class FilterCriteriaCreator {

    public List<FilterCriterion> from(ClassFiltersDto filtersDto) {
        return Arrays.stream(filtersDto.getClass().getDeclaredFields())
                .filter(field -> isFieldNonNull(field, filtersDto))
                .filter(field -> isComplexCriterionName(field.getName()))
                .map(field -> fromField(field, filtersDto))
                .toList();
    }

    private boolean isFieldNonNull(Field field, ClassFiltersDto instance) {
        field.setAccessible(true);
        try {
            return field.get(instance) != null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error during creating filter criteria, cannot access field: " + field.getName());
        }
    }

    private boolean isComplexCriterionName(String name) {
        var matcher = Pattern.compile("[A-Z]?[a-z]+").matcher(name);
        var matches = new ArrayList<String>();
        while (matcher.find()) matches.add(matcher.group());
        return matches.size() != 2 &&
                isValidEnumValue(CourseType.class, matches.get(0).toUpperCase()) &&
                isValidEnumValue(PointType.class, matches.get(1).toUpperCase());
    }

    private <T extends Enum<T>> boolean isValidEnumValue(Class<T> e, String str) {
        try {
            T.valueOf(e, str);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private FilterCriterion fromField(Field field, ClassFiltersDto instance) {
        var splitName = splitCriterionName(field.getName());
        field.setAccessible(true);
        try {
            return FilterCriterion.builder()
                    .courseType(CourseType.valueOf(splitName[0].toUpperCase()))
                    .pointType(PointType.valueOf(splitName[1].toUpperCase()))
                    .requiredAmount((Integer) field.get(instance))
                    .build();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] splitCriterionName(String name) {
        var matcher = Pattern.compile("[A-Z]?[a-z]+").matcher(name);
        var result = new String[2];
        for (int i = 0; i < 2; i++) {
            if (!matcher.find()) throw new IllegalArgumentException("Name cannot be split: " + name);
            result[i] = matcher.group();
        }
        return result;
    }
}
