package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.model.entity.Semester;

import com.example.studyplanscreator.service.FacultyService;
import com.example.studyplanscreator.service.SemesterService;
import com.example.studyplanscreator.service.PlanService;
import com.example.studyplanscreator.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.*;

@Controller
@RequiredArgsConstructor
public class SemesterController {
    private final SemesterService semesterService;
    private final PlanService planService;
    private final FacultyService facultyService;
    private final ClassService classService;

    @GetMapping("/semesters-in-plan")
    private String semestersList(Model model, @RequestParam(required = true) Long planId,
                                 String semesterError) {
        var foundSemesters = semesterService.getSemestersFromPlan(planId);

        //sort semesters so that view can only display them
        foundSemesters.sort((s1, s2) -> s1.getNumber().compareTo(s2.getNumber()));

        var foundPlan = planService.getPlanById(planId);

        model.addAttribute("plan", foundPlan);
        model.addAttribute("semesters", foundSemesters);
        model.addAttribute("faculties", facultyService.getAll());
        if(semesterError != null){
            model.addAttribute("semesterError", semesterError);
            System.out.println(semesterError);
        }

        //add empty semester model so that th can recognise it in the form
        model.addAttribute("semester", new Semester());
        return "semesters/semesters-in-plan";
    }

    @PostMapping("/semester/create")
    private String createSemester(@ModelAttribute Semester semester, RedirectAttributes redirectAttributes){
        Plan semesterPlan = semester.getPlan();
        List<Semester> semestersInPlan = semesterService.getSemestersFromPlan(semesterPlan.getId());

        if(semestersInPlan.stream().anyMatch(element -> element.getNumber() == semester.getNumber())){
            redirectAttributes.addAttribute("semesterError", "Podany semestr już istnieje");
        }
        else semesterService.create(semester);

        redirectAttributes.addAttribute("planId", semester.getPlan().getId());

        return "redirect:/semesters-in-plan";
    }

    @PostMapping("/delete-semester")
    private String deleteSemester(@ModelAttribute Semester semester, RedirectAttributes redirectAttributes){
        semesterService.delete(semester);

        redirectAttributes.addAttribute("planId", semester.getPlan().getId());

        return "redirect:/semesters-in-plan";
    }

    @GetMapping("/semester")
    private String semester(Model model, @RequestParam(required = true) Long plan_id, @RequestParam(required = true) Integer semester_number) {
        var plan = planService.getPlanById(plan_id);
        var semester = semesterService.getSemesterByNumberAndPlan(semester_number, plan);
        model.addAttribute("semester", semester);
        model.addAttribute("plan", plan);
        model.addAttribute("course", classService.getByCategoryAndSemester(COURSE, semester));
        model.addAttribute("group", classService.getByCategoryAndSemester(GROUP, semester));
        model.addAttribute("module", classService.getByCategoryAndSemester(MODULE, semester));
        return "semesters/semester";
    }

    @GetMapping("/view-classes")
    private String viewClasses(Model model,@RequestParam(required = true) Integer semester_number, @RequestParam(required = true) Long plan_id, @RequestParam(required = false) String class_name, @RequestParam(required = false) String class_type){

        var plan = planService.getPlanById(plan_id);
        var semester = semesterService.getSemesterByNumberAndPlan(semester_number, plan);
        model.addAttribute("semester", semester);
        model.addAttribute("plan", plan);
        model.addAttribute("semester_number", semester_number);
        model.addAttribute("plan_id", plan_id);

        if(class_type != null)
        {
            switch (class_type)
            {
                case "course":
                    var courses = classService.getByCategory(COURSE);

                    if(class_name != null){
                        courses = classService.findByNameLikeAndCategory(class_name,COURSE);
                    }
                    model.addAttribute("courses",courses);
                    return "semesters/view-classes-courses";

                case "group":
                    var group = classService.getByCategory(GROUP);

                    if(class_name != null){
                        group = classService.findByNameLikeAndCategory(class_name,GROUP);
                    }
                    model.addAttribute("courses",group);
                    return "semesters/view-classes-courses";

                case "module":
                    var module = classService.getByCategory(MODULE);

                    if(class_name != null){
                        module = classService.findByNameLikeAndCategory(class_name,MODULE);
                    }

                    model.addAttribute("courses",module);
                    return "semesters/view-classes-modules";

                default:
                    return "semesters/view-classes";
            }
        }
        return "semesters/view-classes";
    }
    @GetMapping("/add-class")
    private String addClass(Model model,@RequestParam(required = true) Integer semester_number, @RequestParam(required = true) Long plan_id, @RequestParam(required = false) String class_name, @RequestParam(required = false) String class_type){

        var plan = planService.getPlanById(plan_id);
        var semester = semesterService.getSemesterByNumberAndPlan(semester_number, plan);
        model.addAttribute("semester", semester);
        model.addAttribute("plan", plan);
        model.addAttribute("semester_number", semester_number);
        model.addAttribute("plan_id", plan_id);

        if(class_type != null)
        {

            switch (class_type)
            {
                case "course":
                    var courses = classService.getByCategory(COURSE);

                    if(class_name != null){
                        courses = classService.findByNameLikeAndCategory(class_name,COURSE);
                        for (int i =0; i < courses.size(); i++)
                        {
                            if(courses.get(i).getSemester() == semester)
                            {
                                courses.remove(i);
                                i--;
                            }
                        }
                    }
                    model.addAttribute("courses",courses);
                    return "semesters/add-class-course";

                case "group":
                    var group = classService.getByCategory(GROUP);

                    if(class_name != null){
                        group = classService.findByNameLikeAndCategory(class_name,GROUP);
                        for (int i =0; i < group.size(); i++)
                        {
                            if(group.get(i).getSemester() == semester)
                            {
                                group.remove(i);
                                i--;
                            }
                        }
                    }
                    model.addAttribute("courses",group);
                    return "semesters/add-class-course";

                case "module":
                    var module = classService.getByCategory(MODULE);

                    if(class_name != null){
                        module = classService.findByNameLikeAndCategory(class_name,MODULE);
                    }
                    for (int i =0; i < module.size(); i++)
                    {
                        if(module.get(i).getSemester() == semester)
                        {
                            module.remove(i);
                            i--;
                        }
                    }
                    model.addAttribute("courses",module);
                    return "semesters/add-class-module";

                default:
                    return "semesters/add-class";
            }
        }
        return "semesters/add-class";
    }

    @PostMapping("/add-class-to-semester")
    private String addToSemester(@RequestParam Long semester_id, @RequestParam Long class_id , RedirectAttributes redirectAttributes){
        var semester = semesterService.getSemesterById(semester_id);
        var classes = classService.getClassById(class_id);
        classService.addToSemester(semester, classes);
        redirectAttributes.addAttribute("plan_id", semester.getPlan().getId());
        redirectAttributes.addAttribute("semester_number", semester.getNumber());
        return "redirect:/semester";
    }

    @PostMapping("/delete-class-from-semester")
    private String deleteFromSemester(@RequestParam Long semester_id, @RequestParam Long class_id , RedirectAttributes redirectAttributes){
        var semester = semesterService.getSemesterById(semester_id);
        var classes = classService.getClassById(class_id);
        classService.deleteFromSemester(class_id);
        redirectAttributes.addAttribute("plan_id", semester.getPlan().getId());
        redirectAttributes.addAttribute("semester_number", semester.getNumber());
        return "redirect:/semester";
    }

}