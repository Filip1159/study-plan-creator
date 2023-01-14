package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.EducationLevel;
import com.example.studyplanscreator.model.entity.Faculty;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Long> {
    List<Plan> getPlansByAuthorsContainingAndFacultyAndLevelAndAcademicYear(
            User author, Faculty faculty, EducationLevel level, String academicYear);

    Plan getPlanById(long id);
}
