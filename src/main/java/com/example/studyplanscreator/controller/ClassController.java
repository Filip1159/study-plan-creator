package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.controller.dto.CourseResponse;
import com.example.studyplanscreator.controller.dto.CourseResponseMapper;
import com.example.studyplanscreator.model.Course;
import com.example.studyplanscreator.model.entity.*;
import com.example.studyplanscreator.service.ClassEntityToDomainMapper;
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
public class ClassController {
    private final ClassService service;
    private final ClassEntityToDomainMapper mapper;
    private final LearningEffectService learningEffectService;
    private final TranslationService translationService;
    private final CourseResponseMapper foundCourseResponseMapper;

    @GetMapping("/create-class-form")
    public String createClassForm(Model model, @RequestParam String category, ClassEntity classEntity) {
        System.out.println(classEntity);
        model.addAttribute("waysOfCrediting", translationService.readableNames(WayOfCrediting.values()));
        model.addAttribute("types", translationService.readableNames(Type.values()));
        model.addAttribute("learningEffects", learningEffectService.getAll());
        model.addAttribute("categories", translationService.readableNames(ClassCategory.values()));
        switch (category.toLowerCase()) {
            case "group" -> {
                model.addAttribute("courses",
                        service.getAll().stream()
                                .filter(c -> c.getCategory().equals(COURSE))
                                .map(mapper::courseFromEntity)
                                .map(foundCourseResponseMapper::from)
                                .toList());
                model.addAttribute("class", ClassEntity.builder()
                                .name("Bazy")
                                .ECTS(4)
                                .CNPS(120)
                                .ZZU(60)
                                .wayOfCrediting(WayOfCrediting.EXAM)
                                .type(Type.K)
                                .area("area1")
                                .learningEffects(List.of(
                                        new LearningEffect(1L, "ABCD1234", "x"),
                                        new LearningEffect(3L, "IJKL91011", "x"),
                                        new LearningEffect(4L, "MNOP1213", "x")
                                ))
                        .build());
                return "classes/create-course-group-form";
            }
            case "module" -> {
                classEntity.setCategory(MODULE);
                return "classes/create-course-module-form";
            }
            default -> {
                model.addAttribute("courseTypes", translationService.readableNames(CourseType.values()));
                classEntity.setCategory(COURSE);
                return "classes/create-course-form";
            }
        }
    }

    @PostMapping("/class/create")
    public String createClass(Model model, @Valid @ModelAttribute ClassEntity classEntity, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("waysOfCrediting", translationService.readableNames(WayOfCrediting.values()));
            model.addAttribute("types", translationService.readableNames(Type.values()));
            model.addAttribute("learningEffects", learningEffectService.getAll());
            model.addAttribute("categories", translationService.readableNames(ClassCategory.values()));
            model.addAttribute("courseTypes", translationService.readableNames(CourseType.values()));
            return switch (classEntity.getCategory()) {
                case COURSE -> "classes/create-course-form";
                case GROUP -> "classes/create-course-group-form";
                case MODULE -> "classes/create-course-module-form";
            };
        }
        System.out.println(classEntity);
        service.create(classEntity);
        return "redirect:/";
    }

    @GetMapping("/classes")
    @ResponseBody
    public List<CourseResponse> getWithFilters(ClassFiltersDto requestParams) {
        return service.getWithFilters(requestParams).stream()
                .filter(abstractClass -> abstractClass instanceof Course)
                .map(abstractClass -> foundCourseResponseMapper.from((Course) abstractClass))
                .toList();
    }
}
