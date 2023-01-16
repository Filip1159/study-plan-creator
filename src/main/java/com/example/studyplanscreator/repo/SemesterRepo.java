package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.model.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Long> {
    List<Semester> getSemestersByPlan(Plan plan);

    Semester getSemesterById(long id);

}