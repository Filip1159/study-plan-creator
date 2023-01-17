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

    public List<Plan> getPlansWithFilters(Long facultyId, String field, String authorQuery, EducationLevel level, String academicYear) {
        var faculty = facultyId == null ? null : facultyRepo.findById(facultyId).orElseThrow();
        var author = userRepo.findUserByNameContaining(authorQuery);
        if (author == null) return List.of();
        return planRepo.query(author, faculty, field, level, academicYear);
    }

    public Plan getPlanById(long planId){
        return planRepo.getPlanById(planId);
    }
    
    public void create(Plan plan){
        planRepo.save(plan);
    }
}
