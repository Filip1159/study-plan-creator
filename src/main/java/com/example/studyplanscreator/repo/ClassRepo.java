package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.controller.dto.ClassFiltersDto;
import com.example.studyplanscreator.model.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepo extends JpaRepository<ClassEntity, Long> {

    @Query(
            "SELECT c FROM ClassEntity c " +
            "WHERE (:#{#filters.ects} is null or c.ECTS = :#{#filters.ects}) " +
            "AND (:#{#filters.cnps} is null or c.CNPS = :#{#filters.cnps}) " +
            "AND (:#{#filters.zzu} is null or c.ZZU = :#{#filters.zzu}) " +
            "AND (:#{#filters.category} is null or c.category = :#{#filters.category}) " +
            "AND (:#{#filters.type} is null or c.type = :#{#filters.type}) " +
            "AND (:#{#filters.area} is null or c.area = :#{#filters.area})"
    )
    List<ClassEntity> findByFilters(@Param("filters") ClassFiltersDto filters);
}
