package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.project.ProjectRequest;
import com.khushi.projects.buildly.dto.project.ProjectResponse;
import com.khushi.projects.buildly.dto.project.ProjectSummaryReposne;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryReposne> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long projectId, Long userId);

    ProjectResponse createProject(ProjectRequest request, Long userId);

    ProjectResponse updateProject(Long projectId, ProjectRequest request, Long userId);

    void softDelete(Long userId, Long projectId);
}
