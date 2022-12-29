package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.ClassCategory;
import com.example.studyplanscreator.model.ClassEntity;
import com.example.studyplanscreator.model.Type;
import com.example.studyplanscreator.model.WayOfCrediting;
import com.example.studyplanscreator.service.ClassService;
import com.example.studyplanscreator.service.LearningEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassController {
    private final ClassService service;
    private final LearningEffectService learningEffectService;

    @GetMapping("/create-class-form")
    public String createClassForm(Model model) {
        model.addAttribute("class", new ClassEntity());
        model.addAttribute("waysOfCrediting", WayOfCrediting.values());
        model.addAttribute("types", Type.values());
        model.addAttribute("learningEffects", learningEffectService.getAll());
        model.addAttribute("categories", ClassCategory.values());
        return "classes/create-class-form";
    }

    @PostMapping("/class/create")
    public String createClass(@ModelAttribute ClassEntity classEntity) {
        service.create(classEntity);
        return "redirect:/classes";
    }

    @GetMapping("/classes")
    public String getAllClasses(Model model) {
        model.addAttribute("classes", service.getAll());
        return "classes/all-classes";
    }

    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("hellos", List.of("Hello Filip", "Hello Mateusz", "Hello Stanisław"));
        return "hello";
    }


}
