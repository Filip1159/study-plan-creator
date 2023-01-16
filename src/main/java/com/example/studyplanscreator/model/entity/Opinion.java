package com.example.studyplanscreator.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OpinionStatus status;

    @NotNull
    private String comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
