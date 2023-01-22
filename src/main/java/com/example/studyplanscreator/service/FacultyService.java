package com.example.studyplanscreator.service;

import com.example.studyplanscreator.model.entity.Faculty;
import com.example.studyplanscreator.repo.FacultyRepo;
import com.example.studyplanscreator.service.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepo repo;
    private final ValidatorFactory validatorFactory;

    public List<Faculty> getAll() {
        return repo.findAll();
    }

    public Faculty getFaculty(long facultyId){ return repo.getReferenceById(facultyId);}
}
