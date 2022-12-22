package com.example.studyplanscreator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
}
