package com.example.studyplanscreator.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer number;

    @OneToMany(mappedBy = "semester")
    private List<ClassEntity> classes;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
