package com.example.studyplanscreator.service;

import com.example.studyplanscreator.model.entity.EducationLevel;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.repo.FacultyRepo;
import com.example.studyplanscreator.repo.PlanRepo;
import com.example.studyplanscreator.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepo planRepo;
    private final FacultyRepo facultyRepo;
    private final UserRepo userRepo;

    public List<Plan> getPlansWithFilters(Long facultyId, Long authorId, EducationLevel level, String academicYear,
                                          String status) {
        var faculty = facultyRepo.findById(facultyId).orElseThrow();
        var author = userRepo.findById(authorId).orElseThrow();
        return planRepo.getPlansByAuthorsContainingAndFacultyAndLevelAndAcademicYear(  // TODO add filtering by status
                author, faculty, level, academicYear);
    }

    public Plan getPlanById(long planId){
        return planRepo.getPlanById(planId);
    }
}
