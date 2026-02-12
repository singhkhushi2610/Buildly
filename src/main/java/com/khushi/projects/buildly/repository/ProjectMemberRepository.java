package com.khushi.projects.buildly.repository;

import com.khushi.projects.buildly.entity.ProjectMember;
import com.khushi.projects.buildly.entity.ProjectMemberId;
import com.khushi.projects.buildly.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long ProjectId);

    @Query("""
            SELECT pm.projectRole FROM ProjectMember pm 
            WHERE pm.project.id = :projectId
            AND pm.user.id = :userId
            """)
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,
                                                       @Param("userId") Long userId);

    @Query("""
            SELECT COUNT(pm) FROM ProjectMember pm 
            WHERE pm.id.userId = :userId AND pm.projectRole = :role
            """)
    int countByIdUserIdAndRole(@Param("userId") Long userId, ProjectRole role);

    default int countOfProjectsOwnedByUser(Long userId) {
        return countByIdUserIdAndRole(userId, ProjectRole.OWNER);
    }
}
