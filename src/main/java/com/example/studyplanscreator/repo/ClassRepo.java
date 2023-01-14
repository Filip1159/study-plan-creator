package com.example.studyplanscreator.repo;

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
            "WHERE (:#{#params.name} is null or c.name LIKE %:#{#params.name}%)" +
            "AND (:#{#params.ects} is null or c.ECTS = :#{#params.ects}) " +
            "AND (:#{#params.cnps} is null or c.CNPS = :#{#params.cnps}) " +
            "AND (:#{#params.zzu} is null or c.ZZU = :#{#params.zzu}) " +
            "AND (:#{#params.classCategory} is null or c.category = :#{#params.classCategory}) " +
            "AND (:#{#params.type} is null or c.type = :#{#params.type}) " +
            "AND (:#{#params.area} is null or c.area = :#{#params.area})"
    )
    List<ClassEntity> query(@Param("params") RepoClassesQueryParams params);
}
