package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.entity.ClassCategory;
import com.example.studyplanscreator.model.entity.Type;
import lombok.Builder;

@Builder
public record RepoClassesQueryParams(
        String name,
        Integer ects,
        Integer cnps,
        Integer zzu,
        ClassCategory classCategory,
        Type type,
        String area
) {
    public static RepoClassesQueryParams from(ClassFiltersDto filtersDto) {
        return new RepoClassesQueryParams(filtersDto.getName(), filtersDto.getEcts(), filtersDto.getCnps(),
                filtersDto.getZzu(),
                filtersDto.getCategory() != null ? ClassCategory.valueOf(filtersDto.getCategory()) : null,
                filtersDto.getType() != null ? Type.valueOf(filtersDto.getType()) : null,
                filtersDto.getArea());
    }
}
