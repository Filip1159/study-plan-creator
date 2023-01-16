package com.example.studyplanscreator.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "faculty")
    private List<Plan> plans;

    // added to prevent stack overflow error while calling to string from plan and faculty
    @Override
    public String toString() {
        return "";
    }
}
