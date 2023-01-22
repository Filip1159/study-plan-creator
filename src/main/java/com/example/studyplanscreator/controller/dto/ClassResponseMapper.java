package com.example.studyplanscreator.controller.dto;

import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassResponseMapper {
    private final TranslationService translationService;

    public ClassResponse from(ClassEntity classEntity) {
        return new ClassResponse(
                classEntity.getId(),
                classEntity.getName(),
                classEntity.getECTS(),
                classEntity.getCNPS(),
                classEntity.getZZU(),
                translationService.translate(classEntity.getCategory()));
    }
}
