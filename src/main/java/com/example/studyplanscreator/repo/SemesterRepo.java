package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Long> {
}