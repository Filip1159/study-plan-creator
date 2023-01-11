package com.example.studyplanscreator.service;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.repo.ClassRepo;
import com.example.studyplanscreator.service.filtering.FiltersFactory;
import com.example.studyplanscreator.service.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepo repo;
    private final ValidatorFactory validatorFactory;
    private final FiltersFactory filtersFactory;

    public ClassEntity create(ClassEntity classEntity) {
        validatorFactory.getValidatorFor(classEntity).validate(classEntity);
        return repo.save(classEntity);
    }

    public ClassEntity getById(Long classId) {
        return repo.findById(classId).orElseThrow();
    }

    public List<ClassEntity> getAll() {
        return repo.findAll();
    }

    public List<ClassEntity> getWithFilters(ClassFiltersDto filters) {
        var preFilteredClasses = repo.findByFilters(filters);
        return preFilteredClasses.stream()
//                .filter(classEntity -> filtersFactory.getFor(classEntity).matchesFilters())  // TODO
                .toList();
    }


    public ClassEntity update(Long classId, ClassEntity classEntity) {
        var existingClass = repo.findById(classId).orElseThrow();
        existingClass.setArea(classEntity.getArea());
        existingClass.setCNPS(classEntity.getCNPS());
        existingClass.setCategory(classEntity.getCategory());
        existingClass.setECTS(classEntity.getECTS());
        existingClass.setName(classEntity.getName());
        existingClass.setSemester(classEntity.getSemester());
        existingClass.setZZU(classEntity.getZZU());
        existingClass.setType(classEntity.getType());
        existingClass.setWayOfCrediting(classEntity.getWayOfCrediting());
        return repo.save(existingClass);
    }

    public void delete(Long classId) {
        repo.deleteById(classId);
    }
}
