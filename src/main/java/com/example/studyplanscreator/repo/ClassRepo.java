package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.ClassCategory;
import com.example.studyplanscreator.model.entity.ClassEntity;
import com.example.studyplanscreator.model.entity.Plan;
import com.example.studyplanscreator.model.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepo extends JpaRepository<ClassEntity, Long> {

    @Query(
            "SELECT c FROM ClassEntity c " +
            "WHERE (:#{#params.name} is not null and :#{#params.name} != '' and lower(c.name) LIKE lower(concat('%', :#{#params.name}, '%'))) " +
            "AND (:#{#params.ects} is null or c.ECTS = :#{#params.ects}) " +
            "AND (:#{#params.cnps} is null or c.CNPS = :#{#params.cnps}) " +
            "AND (:#{#params.zzu} is null or c.ZZU = :#{#params.zzu}) " +
            "AND (:#{#params.classCategory} is null or c.category = :#{#params.classCategory}) " +
            "AND (:#{#params.type} is null or c.type = :#{#params.type}) " +
            "AND (:#{#params.area} is null or c.area = :#{#params.area}) " +
            "AND c.groupId is null"
    )

    List<ClassEntity> query(@Param("params") RepoClassesQueryParams params);

    @Query("SELECT c FROM ClassEntity c WHERE (:category is null or :category = c.category) ")
    List<ClassEntity> getByCategory(ClassCategory category);

    @Query("SELECT c FROM ClassEntity c WHERE (:category is null or :category = c.category) " + "AND (:semester is null or :semester = c.semester) ")
    List<ClassEntity> getByCategoryAndSemester(ClassCategory category, Semester semester);

    @Modifying
    @Query("UPDATE ClassEntity c SET c.semester = :semester WHERE c.id = :class_id")
    public void addToSemester(Semester semester, Long class_id);

    @Modifying
    @Query("UPDATE ClassEntity c SET c.semester = null WHERE c.id = :class_id")
    public void deleteFromSemester(Long class_id);

    public List<ClassEntity> findByNameContainingAndCategory(String name, ClassCategory category);

    public ClassEntity getClassById(Long class_id);



}
