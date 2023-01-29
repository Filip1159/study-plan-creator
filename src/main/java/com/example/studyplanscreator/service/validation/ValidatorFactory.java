package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.model.entity.ClassEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidatorFactory {
    private final CourseGroupValidator courseGroupValidator;
    private final CourseModuleValidator courseModuleValidator;
    private final CourseValidator courseValidator;

    public ClassEntityValidator getValidatorFor(ClassEntity classEntity) {
        return switch (classEntity.getCategory()) {
            case GROUP -> courseGroupValidator;
            case MODULE -> courseModuleValidator;
            case COURSE -> courseValidator;
        };
    }
}
