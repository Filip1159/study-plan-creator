package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Long> {

    @Query(
            "SELECT s FROM Semester s " +
                    "WHERE (:number is null or :number = s.number) " +
                    "AND (:classes is null or :classes = s.classes) " +
                    "AND (:plan is null or :plan = s.plan) "
    )
    List<Semester> query(
            @Param("number") Integer number,
            @Param("classes") List<ClassEntity> classes,
            @Param("plan") Plan plan);

    List<Semester> getSemestersByPlan(Plan plan);


    @Query("SELECT s FROM Semester s WHERE (:number is null or :number = s.number) " + "AND (:plan is null or :plan = s.plan) ")
    Semester getSemesterByNumberAndPlan(Integer number, Plan plan);

    public Semester getSemesterById(Long id);
}