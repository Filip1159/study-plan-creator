package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.AbstractClass;
import com.example.studyplanscreator.model.entity.*;
import com.example.studyplanscreator.service.ClassService;
import com.example.studyplanscreator.service.LearningEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.*;

@Controller
@RequiredArgsConstructor
public class ClassController {
    private final ClassService service;
    private final LearningEffectService learningEffectService;

    @GetMapping("/create-class-form")
    public String createClassForm(Model model, @RequestParam String category) {
        model.addAttribute("waysOfCrediting", WayOfCrediting.values());
        model.addAttribute("types", Type.values());
        model.addAttribute("learningEffects", learningEffectService.getAll());
        model.addAttribute("categories", ClassCategory.values());
        switch (category.toLowerCase()) {
            case "group" -> {
                model.addAttribute("courses",
                        service.getAll().stream()
                                .map(classEntity -> classEntity.getName() + " " + classEntity.getCourseType().name())
                                .toList());
                model.addAttribute("class", ClassEntity.builder().category(GROUP).build());
                return "classes/create-course-group-form";
            }
            case "module" -> {
                model.addAttribute("class", ClassEntity.builder().category(MODULE).build());
                return "classes/create-course-module-form";
            }
            default -> {
                model.addAttribute("courseTypes", CourseType.values());
                model.addAttribute("class", ClassEntity.builder().category(COURSE).build());
                return "classes/create-course-form";
            }
        }
    }

    @PostMapping("/class/create")
    public String createClass(@ModelAttribute ClassEntity classEntity) {
        service.create(classEntity);
        return "redirect:/classes";
    }

    @GetMapping("/classes")
    @ResponseBody
    public List<AbstractClass> getWithFilters(ClassFiltersDto requestParams) {  // TODO add filtering
        return service.getWithFilters(requestParams);
    }
}
