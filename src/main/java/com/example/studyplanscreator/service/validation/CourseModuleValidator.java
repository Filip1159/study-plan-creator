package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.exception.*;
import com.example.studyplanscreator.model.entity.ClassEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.studyplanscreator.service.validation.ValidationUtils.deepEqual;

@Service
public class CourseModuleValidator implements ClassEntityValidator {

    @Override
    public void validate(ClassEntity classEntity) {
        validateCoursesAssigned(classEntity);
        validatePointSums(classEntity);
        validateType(classEntity);
        validateLearningEffectRealisation(classEntity);
    }

    private void validateCoursesAssigned(ClassEntity classEntity) {
        if (classEntity.getCoursesInModule() == null || classEntity.getCoursesInModule().isEmpty()) {
            throw new GroupOrModuleEmptyException();
        }
    }

    private void validateType(ClassEntity classEntity) {
        classEntity.getCoursesInModule().forEach(course -> {
            if (!course.getType().equals(classEntity.getType())) throw new GroupOrModuleTypeMismatchException();
        });
    }

    private void validateLearningEffectRealisation(ClassEntity classEntity) {
        classEntity.getCoursesInModule().forEach(course -> {
            if (!deepEqual(course.getLearningEffects(), classEntity.getLearningEffects()))
                throw new LearningEffectMismatchException();
        });
    }

    private void validatePointSums(ClassEntity classEntity) {
        if (!validateECTS(classEntity)) {
            throw new ECTSMismatchException();
        } else if (!validateCNPS(classEntity)) {
            throw new CNPSMismatchException();
        } else if (!validateZZU(classEntity)) {
            throw new ZZUMismatchException();
        }
    }


    private boolean validateECTS(ClassEntity classEntity) {
        return classEntity.getCoursesInModule().stream().allMatch(course -> Objects.equals(course.getECTS(), classEntity.getECTS()));
    }

    private boolean validateCNPS(ClassEntity classEntity) {
        return classEntity.getCoursesInModule().stream().allMatch(course -> Objects.equals(course.getCNPS(), classEntity.getCNPS()));
    }

    private boolean validateZZU(ClassEntity classEntity) {
        return classEntity.getCoursesInModule().stream().allMatch(course -> Objects.equals(course.getZZU(), classEntity.getZZU()));
    }
}
