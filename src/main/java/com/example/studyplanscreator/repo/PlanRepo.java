package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.EducationLevel;
import com.example.studyplanscreator.model.entity.Faculty;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Long> {

    @Query(
            "SELECT p FROM Plan p " +
            "WHERE (:author is null or :author member of p.authors) " +
            "AND (:faculty is null or :faculty = p.faculty) " +
            "AND (:field is null or :field = p.field) " +
            "AND (:level is null or :level = p.level) " +
            "AND (:year is null or :year = p.academicYear)"
    )
    List<Plan> query(
            @Param("author") User author,
            @Param("faculty") Faculty faculty,
            @Param("field") String field,
            @Param("level") EducationLevel level,
            @Param("year") String academicYear);

    Plan getPlanById(long id);

    Plan getPlanByNameAndFieldAndFacultyAndLevelAndAcademicYear(String name, String field, Faculty faculty, EducationLevel level, String academicYear);
}
