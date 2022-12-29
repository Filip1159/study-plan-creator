package com.example.studyplanscreator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class LearningEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String description;

    @ManyToMany(mappedBy = "learningEffects")
    private List<ClassEntity> realisingClasses;
}
