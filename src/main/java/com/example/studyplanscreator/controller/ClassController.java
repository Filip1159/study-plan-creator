package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.*;
import com.example.studyplanscreator.service.ClassService;
import com.example.studyplanscreator.service.LearningEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.example.studyplanscreator.model.ClassCategory.*;

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
    public String getAllClasses(Model model, @RequestParam(required = false) Integer ects,
                                @RequestParam(required = false) Integer cnps,
                                @RequestParam(required = false) Integer zzu,
                                @RequestParam(required = false) String category,
                                @RequestParam(required = false) Long[] learningEffects,
                                @RequestParam(required = false) String type,
                                @RequestParam(required = false) String area,
                                @RequestParam(required = false) Long lectureEcts,
                                @RequestParam(required = false) Long projectEcts,
                                @RequestParam(required = false) Long labEcts,
                                @RequestParam(required = false) Long exercisesEcts,
                                @RequestParam(required = false) Long seminaryEcts,
                                @RequestParam(required = false) Long lectureCnps,
                                @RequestParam(required = false) Long projectCnps,
                                @RequestParam(required = false) Long labCnps,
                                @RequestParam(required = false) Long exercisesCnps,
                                @RequestParam(required = false) Long seminaryCnps,
                                @RequestParam(required = false) Long lectureZzu,
                                @RequestParam(required = false) Long projectZzu,
                                @RequestParam(required = false) Long labZzu,
                                @RequestParam(required = false) Long exercisesZzu,
                                @RequestParam(required = false) Long seminaryZzu) {  // TODO add filtering
        model.addAttribute("classes", service.getAll());
        return "classes/all-classes";
    }

    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("hellos", List.of("Hello Filip", "Hello Mateusz", "Hello Stanisław"));
        return "hello";
    }


}
