package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.project.ProjectRequest;
import com.khushi.projects.buildly.dto.project.ProjectResponse;
import com.khushi.projects.buildly.dto.project.ProjectSummaryReposne;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryReposne> getUserProjects();

    ProjectResponse getUserProjectById(Long projectId);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long projectId, ProjectRequest request);

    void softDelete(Long projectId);
}
