package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.*;
import com.example.studyplanscreator.service.SemesterService;
import com.example.studyplanscreator.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SemesterController {
    private final SemesterService semesterService;

    private final PlanService planService;

    @GetMapping("/semesters-in-plan")
    private String semestersList(Model model, @RequestParam(required = true) Long planId) {
        var foundSemesters = semesterService.getSemestersFromPlan(planId);
        var foundPlan = planService.getPlanById(planId);

        model.addAttribute("plan", foundPlan);
        model.addAttribute("semesters", foundSemesters);
        return "semesters/semesters-in-plan";
    }
}