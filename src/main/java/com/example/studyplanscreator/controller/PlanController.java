package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.PlanFilters;
import com.example.studyplanscreator.controller.dto.PlanResponseMapper;
import com.example.studyplanscreator.model.entity.EducationLevel;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.service.FacultyService;
import com.example.studyplanscreator.service.PlanService;
import com.example.studyplanscreator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
    private final FacultyService facultyService;
    private final TranslationService translationService;
    private final PlanResponseMapper mapper;

    @GetMapping("/plans")
    private String plansList(Model model, PlanFilters planFilters) {
        var foundPlans = planService.getPlansWithFilters(planFilters.facultyId(), planFilters.field(),
                planFilters.author(), planFilters.level(), planFilters.academicYear());
        model.addAttribute("plans", foundPlans.stream().map(mapper::from).toList());
        model.addAttribute("filters", planFilters);
        model.addAttribute("faculties", facultyService.getAll());
        model.addAttribute("levels", translationService.readableNames(EducationLevel.values()));
        return "plans/plans-list";
    }

    @GetMapping("/create-plan-form")
    public String createPlanForm(Model model) {
        model.addAttribute("plan", new Plan());
        model.addAttribute("faculties", facultyService.getAll());
        model.addAttribute("levels", EducationLevel.values());
        return "plans/create-plan-form";
    }

    @PostMapping("/plan/create")
    public String createPlan(@ModelAttribute Plan plan) {
        planService.create(plan);
        return  "plans/create-plan-form";
        //return "redirect:/plans";
    }


}
