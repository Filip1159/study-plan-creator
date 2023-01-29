package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.model.entity.ClassEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseValidator implements ClassEntityValidator {
    @Override
    public void validate(ClassEntity classEntity) {
        // nothing to validate :)
    }
}
