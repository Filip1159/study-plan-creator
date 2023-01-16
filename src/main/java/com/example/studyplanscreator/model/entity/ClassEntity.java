package com.example.studyplanscreator.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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

    @NotNull
    private String name;

    @PositiveOrZero
    private Integer ECTS;

    @Positive
    private Integer CNPS;

    @Positive
    private Integer ZZU;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WayOfCrediting wayOfCrediting;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    private String area;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassCategory category;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

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
