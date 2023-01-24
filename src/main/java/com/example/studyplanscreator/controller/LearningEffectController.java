package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.model.entity.LearningEffect;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.service.LearningEffectService;
import com.example.studyplanscreator.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LearningEffectController {
    private final PlanService planService;
    private final LearningEffectService learningEffectService;

    @GetMapping("/learning-effects")
    private String learningEffects(Model model, String deleteLearningEffectErrorMessage) {
        if(deleteLearningEffectErrorMessage != null) model.addAttribute("deleteLearningEffectErrorMessage", deleteLearningEffectErrorMessage);
        model.addAttribute("learningEffects", learningEffectService.getAll());
        return "learning-effects/learning-effects";
    }

    @GetMapping("/create-learning-effect")
    public String createLearningEffect(Model model, LearningEffect learningEffect, String learningEffectCreateError) {
        if(learningEffect != null) model.addAttribute("learningEffect", learningEffect);
        else model.addAttribute("learningEffect", new LearningEffect());

        if(learningEffectCreateError != null) model.addAttribute("learningEffectCreateError", learningEffectCreateError);
        return "learning-effects/create-learning-effect-form";
    }

    @PostMapping("/create-learning-effect")
    public String createLearningEffect(Model model, @ModelAttribute LearningEffect learningEffect, RedirectAttributes redirectAttributes) {
        if(learningEffect.getSymbol().isEmpty()) {
            redirectAttributes.addAttribute("learningEffectCreateError", "Symbol kształcenia się nie może być pusty");
            redirectAttributes.addFlashAttribute("learningEffect", learningEffect);
            return "redirect:/create-learning-effect";
        }
        if(learningEffectService.getLearningEffectBySymbol(learningEffect.getSymbol()) != null){
            redirectAttributes.addAttribute("learningEffectCreateError", "Efekt kształcenia o podanym symbolu już istnieje");
            redirectAttributes.addFlashAttribute("learningEffect", learningEffect);
            return "redirect:/create-learning-effect";
        }

        learningEffectService.create(learningEffect);
        return "redirect:/learning-effects";
    }


    @GetMapping("/modify-learning-effect-form")
    public String modifyLearningEffectForm(Model model, Long id) {
        LearningEffect learningEffect = learningEffectService.getLearningEffectById(id);
        model.addAttribute("learningEffect", learningEffect);
        return "learning-effects/modify-learning-effect-form";
    }

    @PostMapping("/modify-learning-effect")
    public String modifyLearningEffect(Model model, @ModelAttribute LearningEffect learningEffect) {
        learningEffectService.create(learningEffect);
        return "redirect:/learning-effects";
    }

    @PostMapping("/delete-learning-effect")
    public String deleteLearningEffect(Model model, long id, RedirectAttributes redirectAttributes) {

        if(!learningEffectService.delete(id)) redirectAttributes.addAttribute("deleteLearningEffectErrorMessage", "Nie można usunąć podanego efektu uczenia się. Jest on obecnie używany.");

        return "redirect:/learning-effects";
    }

}
