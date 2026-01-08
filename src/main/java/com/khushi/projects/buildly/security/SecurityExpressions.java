package com.khushi.projects.buildly.security;

import com.khushi.projects.buildly.entity.Project;
import com.khushi.projects.buildly.enums.ProjectPermission;
import com.khushi.projects.buildly.enums.ProjectRole;
import com.khushi.projects.buildly.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.khushi.projects.buildly.enums.ProjectPermission.*;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {

    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    private boolean hasPermission(Long projectId, ProjectPermission projectPermission) {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
                .map(role -> role.getPermissions().contains(projectPermission))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId) {
        return hasPermission(projectId, VIEW);
    }

    public boolean canEditProject(Long projectId) {
        return hasPermission(projectId, EDIT);
    }

    public boolean canDeleteProject(Long projectId) {
        return hasPermission(projectId, DELETE);
    }

    public boolean canManageMembers(Long projectId) {
        return hasPermission(projectId, MANAGE_MEMBERS);
    }

    public boolean canViewMembers(Long projectId) {
        return hasPermission(projectId, VIEW_MEMBERS);
    }
}
