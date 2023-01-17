package com.example.studyplanscreator.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class LearningEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String description;

    @ManyToMany(mappedBy = "learningEffects", cascade = CascadeType.MERGE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<ClassEntity> realisingClasses;

    public LearningEffect(Long id, String symbol, String description) {
        this.id = id;
        this.symbol = symbol;
        this.description = description;
    }
}
