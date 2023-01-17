package com.example.studyplanscreator.repo;

import com.example.studyplanscreator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(
            "SELECT u FROM User u " +
            "WHERE lower(concat(u.name, ' ', u.surname)) like lower(concat('%', :query, '%'))"
    )
    User findUserByNameContaining(@Param("query") String query);
}
