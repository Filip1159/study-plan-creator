package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.exception.*;
import com.example.studyplanscreator.model.entity.ClassEntity;
import org.springframework.stereotype.Service;

import static com.example.studyplanscreator.service.validation.ValidationUtils.deepEqual;

@Service
public class CourseModuleValidator implements ClassEntityValidator {

    @Override
    public void validate(ClassEntity classEntity) {
        validatePointSums(classEntity);
        validateType(classEntity);
        validateLearningEffectRealisation(classEntity);
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
        if (classEntity.getECTS() != sumECTS(classEntity)) {
            throw new ECTSMismatchException();
        } else if (classEntity.getCNPS() != sumCNPS(classEntity)) {
            throw new CNPSMismatchException();
        } else if (classEntity.getZZU() != sumZZU(classEntity)) {
            throw new ZZUMismatchException();
        }
    }


    private int sumECTS(ClassEntity classEntity) {
        return classEntity.getCoursesInGroup().stream().reduce(0, (accumulator, course) -> accumulator + course.getECTS(), Integer::sum);
    }

    private int sumCNPS(ClassEntity classEntity) {
        return classEntity.getCoursesInGroup().stream().reduce(0, (accumulator, course) -> accumulator + course.getCNPS(), Integer::sum);
    }

    private int sumZZU(ClassEntity classEntity) {
        return classEntity.getCoursesInGroup().stream().reduce(0, (accumulator, course) -> accumulator + course.getZZU(), Integer::sum);
    }
}
