package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.controller.dto.CourseResponse;
import com.example.studyplanscreator.controller.dto.CourseResponseMapper;
import com.example.studyplanscreator.model.Course;
import com.example.studyplanscreator.model.entity.*;
import com.example.studyplanscreator.service.ClassService;
import com.example.studyplanscreator.service.LearningEffectService;
import com.example.studyplanscreator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("classes")
public class ClassController {
    private final ClassService service;
    private final LearningEffectService learningEffectService;
    private final TranslationService translationService;
    private final CourseResponseMapper foundCourseResponseMapper;

    @GetMapping("/create")
    public String createClassForm(Model model, @RequestParam ClassCategory category, ClassEntity classEntity) {
        System.out.println(classEntity);
        model.addAttribute("waysOfCrediting", translationService.readableNames(WayOfCrediting.values()));
        model.addAttribute("types", translationService.readableNames(Type.values()));
        model.addAttribute("learningEffects", learningEffectService.getAll());
        model.addAttribute("categories", translationService.readableNames(ClassCategory.values()));
        return switch (category) {
            case GROUP -> {
                classEntity.setCategory(GROUP);
                yield "classes/create-course-group-form";
            }
            case MODULE -> {
                classEntity.setCategory(MODULE);
                yield "classes/create-course-module-form";
            }
            case COURSE -> {
                model.addAttribute("courseTypes", translationService.readableNames(CourseType.values()));
                classEntity.setCategory(COURSE);
                yield "classes/create-course-form";
            }
        };
    }

    @PostMapping("/create")
    public String createClass(Model model, @RequestParam ClassCategory category, @Valid @ModelAttribute ClassEntity classEntity, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("waysOfCrediting", translationService.readableNames(WayOfCrediting.values()));
            model.addAttribute("types", translationService.readableNames(Type.values()));
            model.addAttribute("learningEffects", learningEffectService.getAll());
            model.addAttribute("categories", translationService.readableNames(ClassCategory.values()));
            model.addAttribute("courseTypes", translationService.readableNames(CourseType.values()));
            return switch (category) {
                case COURSE -> "classes/create-course-form";
                case GROUP -> "classes/create-course-group-form";
                case MODULE -> "classes/create-course-module-form";
            };
        }
        System.out.println(classEntity);
        service.create(classEntity);
        return "redirect:/";
    }

    @GetMapping("/query")
    @ResponseBody
    public List<CourseResponse> query(ClassFiltersDto requestParams) {
        return service.getWithFilters(requestParams).stream()
                .filter(abstractClass -> abstractClass instanceof Course)
                .map(abstractClass -> foundCourseResponseMapper.from((Course) abstractClass))
                .toList();
    }
}
