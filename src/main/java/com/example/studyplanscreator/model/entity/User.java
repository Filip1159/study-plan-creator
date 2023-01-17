package com.example.studyplanscreator.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

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
