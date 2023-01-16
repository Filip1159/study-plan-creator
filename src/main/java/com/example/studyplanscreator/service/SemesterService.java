package com.example.studyplanscreator.service;

import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.model.entity.Semester;
import com.example.studyplanscreator.repo.SemesterRepo;
import com.example.studyplanscreator.repo.PlanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepo semesterRepo;
    private final PlanRepo planRepo;

    public List<Semester> getAll() {
        return semesterRepo.findAll();
    }
    public List<Semester> getSemestersFromPlan(long planId) {
        Plan plan = planRepo.getPlanById(planId);
        return semesterRepo.getSemestersByPlan(plan);
    }

    public void create(Semester semester){
        semesterRepo.save(semester);
    }
    public void delete(Semester semester){ semesterRepo.delete(semester); }

    public Semester getSemesterById(long semesterId) {
        return repo.getSemesterById(semesterId);
    }


}