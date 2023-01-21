package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.model.entity.Plan;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanValidator {

    public static PlanErrorContainer validate(Plan plan) {
        PlanErrorContainer planErrorContainer = new PlanErrorContainer();
        validateYear(planErrorContainer, plan.getAcademicYear());
        validateField(planErrorContainer, plan.getField());
        validateName(planErrorContainer, plan.getName());

        return planErrorContainer;
    }

    private static void validateYear(PlanErrorContainer planErrorContainer, String year){
        Pattern pattern = Pattern.compile("^20[0-9][0-9]/20[0-9][0-9]$");
        Matcher matcher = pattern.matcher(year);

        if(matcher.find()){
            String[] result = year.split("/");
            int prev_year = Integer.parseInt(result[0]);
            int next_year = Integer.parseInt(result[1]);

            if (prev_year+1 != next_year) planErrorContainer.setAcademicYearError("Zły format roku akademickiego.");
        }
        else {
            planErrorContainer.setAcademicYearError("Zły format roku akademickiego. Poprawny format: 'YYYY/YYYY'");
        }
    }

    private static void validateField(PlanErrorContainer planErrorContainer, String field){
        if(field.isEmpty()){
            planErrorContainer.setFieldError("Kierunek nie może być pusty");
        }
    }

    private static void validateName(PlanErrorContainer planErrorContainer, String name){
        if(name.isEmpty()){
            planErrorContainer.setNameError("Nazwa planu nie może być pusta");
        }
    }

}
