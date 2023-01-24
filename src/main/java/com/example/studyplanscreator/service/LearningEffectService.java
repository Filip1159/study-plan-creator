package com.example.studyplanscreator.service;

import com.example.studyplanscreator.model.entity.LearningEffect;
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

    public LearningEffect create(LearningEffect learningEffect){
        return repo.save(learningEffect);
    }

    public boolean delete(long id){
        LearningEffect learningEffect = repo.getLearningEffectById(id);
        if(learningEffect.getRealisingClasses().size() > 0) return false;
        else repo.delete(learningEffect);

        return true;
    }
    public LearningEffect getLearningEffectById(Long id){
        return repo.getLearningEffectById(id);
    }
    public LearningEffect getLearningEffectBySymbol(String symbol){
        return repo.getLearningEffectBySymbol(symbol);
    }
}
