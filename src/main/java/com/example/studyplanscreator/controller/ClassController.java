package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.controller.dto.FoundCourseResponse;
import com.example.studyplanscreator.controller.dto.FoundCourseResponseMapper;
import com.example.studyplanscreator.model.Course;
import com.example.studyplanscreator.model.entity.*;
import com.example.studyplanscreator.service.ClassEntityToDomainMapper;
import com.example.studyplanscreator.service.ClassService;
import com.example.studyplanscreator.service.LearningEffectService;
import com.example.studyplanscreator.service.TranslationService;
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
    private final ClassEntityToDomainMapper mapper;
    private final LearningEffectService learningEffectService;
    private final TranslationService translationService;
    private final FoundCourseResponseMapper foundCourseResponseMapper;

    @GetMapping("/create-class-form")
    public String createClassForm(Model model, @RequestParam String category) {
        model.addAttribute("waysOfCrediting", translationService.readableNames(WayOfCrediting.values()));
        model.addAttribute("types", translationService.readableNames(Type.values()));
        model.addAttribute("learningEffects", learningEffectService.getAll());
        model.addAttribute("categories", translationService.readableNames(ClassCategory.values()));
        switch (category.toLowerCase()) {
            case "group" -> {
                model.addAttribute("courses",
                        service.getAll().stream()
                                .filter(classEntity -> classEntity.getCategory().equals(COURSE))
                                .map(mapper::courseFromEntity)
                                .map(foundCourseResponseMapper::from)
                                .toList());
                model.addAttribute("class", ClassEntity.builder().category(GROUP).build());
                return "classes/create-course-group-form";
            }
            case "module" -> {
                model.addAttribute("class", ClassEntity.builder().category(MODULE).build());
                return "classes/create-course-module-form";
            }
            default -> {
                model.addAttribute("courseTypes", translationService.readableNames(CourseType.values()));
                model.addAttribute("class", ClassEntity.builder().category(COURSE).build());
                return "classes/create-course-form";
            }
        }
    }

    @PostMapping("/class/create")
    public String createClass(@ModelAttribute ClassEntity classEntity) {
        System.out.println(classEntity);
        service.create(classEntity);
        return "redirect:/";
    }

    @GetMapping("/classes")
    @ResponseBody
    public List<FoundCourseResponse> getWithFilters(ClassFiltersDto requestParams) {
        return service.getWithFilters(requestParams).stream()
                .filter(abstractClass -> abstractClass instanceof Course)
                .map(abstractClass -> foundCourseResponseMapper.from((Course) abstractClass))
                .toList();
    }
}
