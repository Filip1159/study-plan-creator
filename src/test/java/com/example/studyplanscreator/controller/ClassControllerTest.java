package com.example.studyplanscreator.controller;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.exception.GroupOrModuleEmptyException;
import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.model.entity.LearningEffect;
import com.example.studyplanscreator.repo.ClassRepo;
import com.example.studyplanscreator.repo.RepoClassesQueryParams;
import com.example.studyplanscreator.service.validation.CourseGroupValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.COURSE;
import static com.example.studyplanscreator.model.entity.CourseType.*;
import static com.example.studyplanscreator.model.entity.Type.K;
import static com.example.studyplanscreator.model.entity.Type.S;
import static com.example.studyplanscreator.model.entity.WayOfCrediting.EXAM;
import static com.example.studyplanscreator.model.entity.WayOfCrediting.PASS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ClassControllerTest {
    @MockBean
    ClassRepo classRepo;

    @Autowired
    ClassController classController;

    @Autowired
    CourseGroupValidator courseGroupValidator;

    @Test
    void shouldFindQueriedClasses() {
        // given
        var existingClasses = List.of(
                new ClassEntity(1L, "Bazy danych", 2, 70, 30, EXAM, K, "area1", COURSE, LECTURE, List.of(), null, null, null, null, null),
                new ClassEntity(2L, "Bazy danych", 2, 60, 30, PASS, K, "area1", COURSE, EXERCISES, List.of(), null, null, null, null, null),
                new ClassEntity(3L, "Bazy danych", 2, 45, 15, PASS, K, "area1", COURSE, LAB, List.of(), null, null, null, null, null)
        );
        var expectedQueryParams = RepoClassesQueryParams.builder().ects(2).build();
        given(classRepo.queryForGroup(expectedQueryParams)).willReturn(existingClasses);

        // when
        var result = classController.queryForGroup(ClassFiltersDto.builder().ects(2).learningEffects(new Long[]{}).build());

        // then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).name()).isEqualTo("Bazy danych");
        assertThat(result.get(1).ECTS()).isEqualTo(2);
        assertThat(result.get(2).courseType()).isEqualTo("Laboratorium");
    }

    @Test
    void shouldFindClassesWithMatchingLearningEffectsOnly() {
        // given
        var matchingLearningEffects = List.of(
                new LearningEffect(1L, "ABC123", "Lorem ipsum 1"),
                new LearningEffect(2L, "DEF456", "Lorem ipsum 2")
        );
        var notMatchingLearningEffects1 = List.of(
                new LearningEffect(1L, "ABC123", "Lorem ipsum 1"),
                new LearningEffect(2L, "DEF456", "Lorem ipsum 2"),
                new LearningEffect(3L, "GHI789", "Lorem ipsum 3")
        );
        var notMatchingLearningEffects2 = List.of(
                new LearningEffect(1L, "ABC123", "Lorem ipsum 1")
        );
        var existingClasses = List.of(
                new ClassEntity(1L, "Bazy danych", 2, 70, 30, EXAM, K, "area1", COURSE, LECTURE, matchingLearningEffects, null, null, null, null, null),
                new ClassEntity(2L, "Bazy danych", 2, 60, 30, PASS, K, "area1", COURSE, EXERCISES, notMatchingLearningEffects1, null, null, null, null, null),
                new ClassEntity(3L, "Bazy danych", 2, 45, 15, PASS, K, "area1", COURSE, LAB, notMatchingLearningEffects2, null, null, null, null, null)
        );

        var expectedQueryParams = RepoClassesQueryParams.builder().name("Bazy").build();
        given(classRepo.queryForGroup(expectedQueryParams)).willReturn(existingClasses);

        // when
        var result = classController.queryForGroup(ClassFiltersDto.builder().name("Bazy").learningEffects(new Long[]{1L, 2L}).build());

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).name()).isEqualTo("Bazy danych");
        assertThat(result.get(0).courseType()).isEqualTo("Wykład");
    }

    @Test
    void shouldValidateCorrectCourseWithoutErrors() {
        // given
        var lecture = new ClassEntity(
                null,
                "Projektowanie oprogramowania",
                2,
                40,
                20,
                PASS,
                S,
                "area2",
                COURSE,
                LECTURE,
                List.of(new LearningEffect(1L, "abc", "Lorem ipsum")),
                null, null, 1L, null, null);
        var lab = new ClassEntity(
                null,
                "Projektowanie oprogramowania",
                2,
                40,
                20,
                PASS,
                S,
                "area2",
                COURSE,
                LAB,
                List.of(new LearningEffect(1L, "abc", "Lorem ipsum")),
                null, null, 1L, null, null);
        var group = new ClassEntity(
                1L,
                "Projektowanie oprogramowania",
                4,
                80,
                40,
                PASS,
                S,
                "area2",
                COURSE,
                LAB,
                List.of(new LearningEffect(1L, "abc", "Lorem ipsum")),
                List.of(lecture, lab),
                null, null, null, null);

        // then
        assertDoesNotThrow(() -> courseGroupValidator.validate(group));
    }

    @Test
    void shouldFailValidationOfGroupWithoutCourses() {
        // given
        var group = new ClassEntity(
                1L,
                "Projektowanie oprogramowania",
                4,
                80,
                40,
                PASS,
                S,
                "area2",
                COURSE,
                LAB,
                List.of(new LearningEffect(1L, "abc", "Lorem ipsum")),
                List.of(),
                null, null, null, null);

        // then
        assertThrows(GroupOrModuleEmptyException.class, () -> courseGroupValidator.validate(group));
    }
}