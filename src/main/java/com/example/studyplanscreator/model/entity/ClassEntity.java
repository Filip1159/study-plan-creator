package com.example.studyplanscreator.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "class")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "nazwa nie może być pusta")
    @NotBlank(message = "nazwa nie może być pusta")
    private String name;

    @PositiveOrZero(message = "liczba ECTS musi być większa lub równa 0")
    @NotNull(message = "liczba ECTS musi być większa lub równa 0")
    private Integer ECTS;

    @Positive(message = "liczba CNPS musi być większa od 0")
    @NotNull(message = "liczba CNPS musi być większa od 0")
    private Integer CNPS;

    @Positive(message = "liczba ZZU musi być większa od 0")
    @NotNull(message = "liczba ZZU musi być większa od 0")
    private Integer ZZU;

    @NotNull(message = "forma zaliczenia nie może być pusta")
    @Enumerated(EnumType.STRING)
    private WayOfCrediting wayOfCrediting;

    @NotNull(message = "typ nie może być pusty")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "obszar nie może być pusty")
    @NotBlank(message = "obszar nie moze być pusty")
    private String area;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassCategory category;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @NotEmpty(message = "kurs musi realizować co najmniej 1 efekt uczenia się")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "learning_effect_realisation",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "learning_effect_id") })
    private List<LearningEffect> learningEffects;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "groupId")
    private List<ClassEntity> coursesInGroup;

    @OneToMany
    @JoinColumn(name = "moduleId")
    private List<ClassEntity> coursesInModule;

    private Long groupId;

    private Long moduleId;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
