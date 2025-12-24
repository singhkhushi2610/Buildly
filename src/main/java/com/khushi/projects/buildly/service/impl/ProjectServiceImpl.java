package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.project.ProjectRequest;
import com.khushi.projects.buildly.dto.project.ProjectResponse;
import com.khushi.projects.buildly.dto.project.ProjectSummaryReposne;
import com.khushi.projects.buildly.entity.Project;
import com.khushi.projects.buildly.entity.ProjectMember;
import com.khushi.projects.buildly.entity.ProjectMemberId;
import com.khushi.projects.buildly.entity.User;
import com.khushi.projects.buildly.enums.ProjectRole;
import com.khushi.projects.buildly.error.ResourceNotFoundException;
import com.khushi.projects.buildly.mapper.ProjectMapper;
import com.khushi.projects.buildly.repository.ProjectMemberRepository;
import com.khushi.projects.buildly.repository.ProjectRepository;
import com.khushi.projects.buildly.repository.UserRepository;
import com.khushi.projects.buildly.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(userId, "User")
        );
        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();
        projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryReposne> getUserProjects(Long userId) {
        var projects = projectRepository.findAllAccessibleProjectsByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long projectId, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow(
                () -> new ResourceNotFoundException(projectId, "Project")
        );
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest request, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        project.setName(request.name());
        Project savedProject = projectRepository.save(project);
        return projectMapper.toProjectResponse(savedProject);
    }

    @Override
    public void softDelete(Long userId, Long projectId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }
}
