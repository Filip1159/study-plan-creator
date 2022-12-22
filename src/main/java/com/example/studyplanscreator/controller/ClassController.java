package com.example.studyplanscreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClassController {

    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("hellos", List.of("Hello Filip", "Hello Mateusz", "Hello Stanisław"));
        return "hello";
    }
}
