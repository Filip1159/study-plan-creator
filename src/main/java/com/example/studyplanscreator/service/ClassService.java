package com.example.studyplanscreator.service;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.AbstractClass;
import com.example.studyplanscreator.model.entity.ClassCategory;
import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.model.entity.LearningEffect;
import com.example.studyplanscreator.model.entity.Semester;
import com.example.studyplanscreator.repo.ClassRepo;
import com.example.studyplanscreator.repo.RepoClassesQueryParams;
import com.example.studyplanscreator.service.filtering.FilterCriteriaCreator;
import com.example.studyplanscreator.service.filtering.FiltersFactory;
import com.example.studyplanscreator.service.validation.ValidationUtils;
import com.example.studyplanscreator.service.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepo repo;
    private final ValidatorFactory validatorFactory;
    private final FiltersFactory filtersFactory;
    private final FilterCriteriaCreator creator;
    private final ClassEntityToDomainMapper mapper;

    public ClassEntity create(ClassEntity classEntity) {
        try {
            validatorFactory.getValidatorFor(classEntity).validate(classEntity);
        } catch (Exception ignored) {}
        System.out.println(classEntity);
        return repo.save(classEntity);
    }

    public ClassEntity getById(Long classId) {
        return repo.findById(classId).orElseThrow();
    }

    public List<ClassEntity> getAll() {
        return repo.findAll();
    }

    public List<ClassEntity> getByCategory(ClassCategory category) {
        return repo.getByCategory(category);
    }

    public List<ClassEntity> findByNameLikeAndCategory(String name, ClassCategory category) {
        return repo.findByNameContainingAndCategory(name, category);
    }
    public List<ClassEntity> getByCategoryAndSemester(ClassCategory category, Semester semester){
        return repo.getByCategoryAndSemester(category,semester);
    }

    public void addToSemester(Semester semester, ClassEntity classes) {
        repo.addToSemester(semester, classes.getId());
    }

    public void deleteFromSemester(Long class_id) {
        repo.deleteFromSemester(class_id);
    }
    public ClassEntity getClassById(Long class_id) {
        return repo.getClassById(class_id);
    }

    public List<AbstractClass> getWithFilters(ClassFiltersDto filtersDto) {
        var preFilteredClasses = repo.query(RepoClassesQueryParams.from(filtersDto));
        preFilteredClasses = preFilteredClasses.stream()
                .filter(classEntity ->
                        ValidationUtils.deepEqual(
                                Arrays.asList(filtersDto.getLearningEffects()),
                                classEntity.getLearningEffects().stream().map(LearningEffect::getId).toList()))
                .toList();
        var criteria = creator.from(filtersDto);
        System.out.println(criteria);
        return preFilteredClasses.stream()
                .filter(classEntity -> {
                    var abstractClass = mapper.toDomain(classEntity);
                    var filters = filtersFactory.getFor(abstractClass);
                    return filters.matchesFilters(abstractClass, criteria);
                })
                .map(mapper::toDomain)
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
