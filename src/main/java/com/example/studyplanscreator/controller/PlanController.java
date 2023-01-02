package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.EducationLevel;
import com.example.studyplanscreator.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @GetMapping("/plans")
    private String plansList(Model model, @RequestParam(required = false) Long facultyId,
                             @RequestParam(required = false) Long authorId,
                             @RequestParam(required = false) String level,
                             @RequestParam(required = false) String academicYear,
                             @RequestParam(required = false) String status) {
        var foundPlans = planService.getPlansWithFilters(facultyId, authorId, EducationLevel.valueOf(level), academicYear, status);
        model.addAttribute("plans", foundPlans);
        return "plans/plans-list";
    }
}
