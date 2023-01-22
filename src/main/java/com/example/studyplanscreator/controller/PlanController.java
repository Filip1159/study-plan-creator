package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.PlanFilters;
import com.example.studyplanscreator.controller.dto.PlanResponseMapper;
import com.example.studyplanscreator.model.entity.EducationLevel;
import com.example.studyplanscreator.model.entity.Faculty;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.service.FacultyService;
import com.example.studyplanscreator.service.PlanService;
import com.example.studyplanscreator.service.TranslationService;
import com.example.studyplanscreator.service.validation.PlanErrorContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
    public String createPlanForm(Model model, PlanErrorContainer planErrorContainer) {
        model.addAttribute("plan", new Plan());
        model.addAttribute("faculties", facultyService.getAll());
        model.addAttribute("levels", EducationLevel.values());

        if(planErrorContainer != null){
            if(!(planErrorContainer.getAcademicYearError() == null))
                model.addAttribute("planAcademicYearError", planErrorContainer.getAcademicYearError());
            if(!(planErrorContainer.getNameError() == null))
                model.addAttribute("planNameError", planErrorContainer.getNameError());
            if(!(planErrorContainer.getFieldError() == null))
                model.addAttribute("planFieldError", planErrorContainer.getFieldError());
            if(!(planErrorContainer.getNotUniqueError() == null))
                model.addAttribute("planNotUniqueError", planErrorContainer.getNotUniqueError());
        }

        return "plans/create-plan-form";
    }

    @PostMapping("/plan/create")
    public String createPlan(Model model, @ModelAttribute Plan plan, RedirectAttributes redirectAttributes) {
        PlanErrorContainer planErrorContainer = planService.create(plan);

        // plan is valid
        if(planErrorContainer.isValid()) return "redirect:/plans";

        // errors occured
        else redirectAttributes.addFlashAttribute("planErrorContainer", planErrorContainer);

        return "redirect:/create-plan-form";
    }

    //cancel parameter for cancel button in form
    @RequestMapping(value="/plan/create", params = "cancel", method = RequestMethod.POST)
    public String cancelCreatePlan(HttpServletRequest request) {
        return "redirect:/plans";
    }

    @PostMapping("/plan/modify")
    public String modifyPlan(Model model, long planId, long facultyId, RedirectAttributes redirectAttributes) {
        Plan plan = planService.getPlanById(planId);
        Faculty faculty = facultyService.getFaculty(facultyId);

        plan.setFaculty(faculty);
        planService.update(plan);

        redirectAttributes.addAttribute("planId", plan.getId());

        return "redirect:/semesters-in-plan";
    }
}
