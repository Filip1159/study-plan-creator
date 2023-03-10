package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.exception.*;
import com.example.studyplanscreator.model.entity.ClassEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.example.studyplanscreator.service.validation.ValidationUtils.deepEqual;

@Service
public class CourseGroupValidator implements ClassEntityValidator {

    @Override
    public void validate(ClassEntity classEntity) {
        validateCoursesAssigned(classEntity);
        validatePointSums(classEntity);
        validateType(classEntity);
        validateLearningEffectRealisation(classEntity);
    }

    private void validateCoursesAssigned(ClassEntity classEntity) {
        if (classEntity.getCoursesInGroup() == null || classEntity.getCoursesInGroup().isEmpty()) {
            throw new GroupOrModuleEmptyException();
        }
    }

    private void validateLearningEffectRealisation(ClassEntity classEntity) {
        var coursesLearningEffects = classEntity.getCoursesInGroup().stream()
                .flatMap(course -> course.getLearningEffects().stream())
                .collect(Collectors.toSet());
        if (!deepEqual(coursesLearningEffects, classEntity.getLearningEffects()))
            throw new LearningEffectMismatchException();
    }

    public void validateType(ClassEntity classEntity) {
        classEntity.getCoursesInGroup().forEach(course -> {
            if (!course.getType().equals(classEntity.getType())) throw new GroupOrModuleTypeMismatchException();
        });
    }

    public void validatePointSums(ClassEntity classEntity) {
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
