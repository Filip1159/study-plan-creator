package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<ClassEntity, Long> {
}
