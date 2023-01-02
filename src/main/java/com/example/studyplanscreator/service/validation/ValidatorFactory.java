package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.model.ClassEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidatorFactory {
    private final CourseGroupValidator courseGroupValidator;
    private final CourseModuleValidator courseModuleValidator;

    public ClassEntityValidator getValidatorFor(ClassEntity classEntity) {
        return switch (classEntity.getCategory()) {
            case GROUP -> courseGroupValidator;
            case MODULE -> courseModuleValidator;
            default -> throw new RuntimeException("This category has no validator");
        };
    }
}
