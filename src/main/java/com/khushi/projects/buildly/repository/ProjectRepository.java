package com.khushi.projects.buildly.repository;

import com.khushi.projects.buildly.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Fetch all projects accessible to a given user.
     * Access is determined via ProjectMember join entity.
     * Soft-deleted projects are excluded.
     */
    @Query("""
        SELECT p
        FROM Project p
        WHERE p.deletedAt IS NULL
          AND EXISTS (
              SELECT 1
              FROM ProjectMember pm
              WHERE pm.user.id = :userId
                AND pm.project = p
          )
        ORDER BY p.updatedAt DESC
    """)
    List<Project> findAllAccessibleProjectsByUser(@Param("userId") Long userId);

    /**
     * Fetch a single project by ID if the given user has access to it.
     * Access is validated via ProjectMember.
     */
    @Query("""
        SELECT p
        FROM Project p
        WHERE p.id = :projectId
          AND p.deletedAt IS NULL
          AND EXISTS (
              SELECT 1
              FROM ProjectMember pm
              WHERE pm.user.id = :userId
                AND pm.project = p
          )
    """)
    Optional<Project> findAccessibleProjectById(@Param("projectId") Long projectId,
                                                @Param("userId") Long userId);
}
