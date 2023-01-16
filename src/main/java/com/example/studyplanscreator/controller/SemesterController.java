package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.*;

import com.example.studyplanscreator.service.FacultyService;
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
    private String semestersList(Model model, @RequestParam(required = false) Long planId) {
        var foundSemesters = semesterService.getSemestersFromPlan(planId);
        model.addAttribute("semesters", foundSemesters);
        return "semesters/semesters-in-plan";
    }

    @GetMapping("/semester")
    private String semester(Model model, @RequestParam(required = false) Long semesterId) {

        return "semesters/semester";
    }

    @GetMapping("/view-classes")
    private String viewClasses(Model model, @RequestParam(required = false) String class_name, @RequestParam(required = false) String class_type){

        if(class_type != null)
        {
            return switch (class_type) {
                case "course" -> "semesters/view-classes-courses";
                case "group" -> "semesters/view-classes-courses";
                case "module" -> "semesters/view-classes-modules";
                default -> "semesters/view-classes";
            };
        }
        return "semesters/view-classes";
    }

}