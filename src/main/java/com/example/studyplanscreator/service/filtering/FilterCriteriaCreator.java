package com.example.studyplanscreator.service.filtering;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.PointType;
import com.example.studyplanscreator.model.entity.CourseType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class FilterCriteriaCreator {

    public List<FilterCriterion> from(ClassFiltersDto filtersDto) {
        return Arrays.stream(filtersDto.getClass().getDeclaredFields())
                .map(field -> fromField(field, filtersDto))
                .toList();
    }

    private FilterCriterion fromField(Field field, ClassFiltersDto instance) {
        var splitName = splitCriterionName(field.getName());
        field.setAccessible(true);
        try {
            return FilterCriterion.builder()
                    .courseType(CourseType.valueOf(splitName[0]))
                    .pointType(PointType.valueOf(splitName[1]))
                    .requiredAmount((Integer) field.get(instance))
                    .build();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] splitCriterionName(String name) {
        var matcher = Pattern.compile("[A-Z]?[a-z]+").matcher(name);
        var result = new String[2];
        result[0] = matcher.group();
        result[1] = matcher.group();
        return result;
    }
}
