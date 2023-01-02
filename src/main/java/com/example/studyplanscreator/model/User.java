package com.example.studyplanscreator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String username;

    private String jobTitle;

    private String institutionName;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @ManyToMany(mappedBy = "authors")
    private List<Plan> plansAuthored;
}
