package com.example.studyplanscreator.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String field;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EducationLevel level;

    @NotNull
    private String academicYear;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "plan")
    private List<Semester> semesters;

    @OneToMany(mappedBy = "plan")
    private List<Opinion> opinions;

    @OneToOne(mappedBy = "plan")
    private Approval approval;

    @ManyToMany
    @JoinTable(name = "plan_authorship",
            joinColumns = { @JoinColumn(name = "plan_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> authors;
}
