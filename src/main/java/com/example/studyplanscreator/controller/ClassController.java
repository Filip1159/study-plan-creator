package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.*;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("classes")
public class ClassController {
    private final ClassService service;
    private final LearningEffectService learningEffectService;
    private final TranslationService translationService;
    private final ClassResponseMapper classResponseMapper;
    private final CourseResponseMapper courseResponseMapper;
    private final ClassEntityToDomainMapper mapper;

    @GetMapping
    public String allClasses(Model model) {
        var allClasses = new ArrayList<>(service.getAll().stream().map(classResponseMapper::from).toList());
        Collections.reverse(allClasses);
        model.addAttribute("classes", allClasses);
        return "classes/all-classes";
    }

    @GetMapping("/create")
    public String createClassForm(Model model, @RequestParam ClassCategory category, ClassEntity classEntity) {
        addBaseModelAttributes(model);
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
            addBaseModelAttributes(model);
            return switch (category) {
                case COURSE -> "classes/create-course-form";
                case GROUP -> "classes/create-course-group-form";
                case MODULE -> "classes/create-course-module-form";
            };
        }
        try {
            service.create(classEntity);
        } catch (RuntimeException e) {
            addBaseModelAttributes(model);
            return switch (category) {
                case COURSE -> "classes/create-course-form";
                case GROUP -> {
                    model.addAttribute("courseTypeTranslations", TranslationService.courseTypeTranslations);
                    result.addError(new FieldError("classEntity", "coursesInGroup", e.getMessage()));
                    var coursesInGroupIds = classEntity.getCoursesInGroup().stream().reduce("", (acc, c) -> acc + c.getId() + ",", String::concat);
                    model.addAttribute("coursesInGroupIds", coursesInGroupIds.substring(0, coursesInGroupIds.length() - 1));
                    yield "classes/create-course-group-form";
                }
                case MODULE -> {
                    result.addError(new FieldError("classEntity", "coursesInModule", e.getMessage()));
                    var coursesInModuleIds = classEntity.getCoursesInModule().stream().reduce("", (acc, c) -> acc + c.getId() + ",", String::concat);
                    model.addAttribute("coursesInModuleIds", coursesInModuleIds.substring(0, coursesInModuleIds.length() - 1));
                    yield "classes/create-course-module-form";
                }
            };
        }
        return "redirect:/classes";
    }


    @GetMapping("/query/GROUP")
    @ResponseBody
    public List<CourseResponse> queryForGroup(ClassFiltersDto requestParams) {
        return service.getWithFilters(requestParams, GROUP).stream()
                .map(mapper::toDomain)
                .filter(abstractClass -> abstractClass instanceof Course)
                .map(abstractClass -> courseResponseMapper.from((Course) abstractClass))
                .toList();
    }

    @GetMapping("/query/MODULE")
    @ResponseBody
    public List<ClassResponse> queryForModule(ClassFiltersDto requestParams) {
        return service.getWithFilters(requestParams, MODULE).stream()
                .map(classResponseMapper::from)
                .toList();
    }

    private void addBaseModelAttributes(Model model) {
        model.addAttribute("waysOfCrediting", translationService.readableNames(WayOfCrediting.values()));
        model.addAttribute("types", translationService.readableNames(Type.values()));
        model.addAttribute("learningEffects", learningEffectService.getAll());
        model.addAttribute("categories", translationService.readableNames(ClassCategory.values()));
        model.addAttribute("courseTypes", translationService.readableNames(CourseType.values()));
    }
}
