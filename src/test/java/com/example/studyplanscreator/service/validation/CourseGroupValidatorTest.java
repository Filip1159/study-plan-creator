package com.example.studyplanscreator.service.validation;

import com.example.studyplanscreator.exception.GroupOrModuleEmptyException;
import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.model.entity.LearningEffect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.studyplanscreator.model.entity.ClassCategory.COURSE;
import static com.example.studyplanscreator.model.entity.CourseType.LAB;
import static com.example.studyplanscreator.model.entity.CourseType.LECTURE;
import static com.example.studyplanscreator.model.entity.Type.S;
import static com.example.studyplanscreator.model.entity.WayOfCrediting.PASS;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CourseGroupValidatorTest {

    @Autowired
    CourseGroupValidator courseGroupValidator;

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