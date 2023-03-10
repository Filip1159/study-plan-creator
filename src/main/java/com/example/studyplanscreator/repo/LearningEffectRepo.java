package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.LearningEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningEffectRepo extends JpaRepository<LearningEffect, Long> {
    LearningEffect getLearningEffectById(long id);
    LearningEffect getLearningEffectBySymbol(String symbol);

}
