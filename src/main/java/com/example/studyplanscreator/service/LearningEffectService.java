package com.example.studyplanscreator.service;

import com.example.studyplanscreator.model.LearningEffect;
import com.example.studyplanscreator.repo.LearningEffectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningEffectService {
    private final LearningEffectRepo repo;

    public List<LearningEffect> getAll() {
        return repo.findAll();
    }
}
