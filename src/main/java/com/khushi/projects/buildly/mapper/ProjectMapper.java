package com.khushi.projects.buildly.mapper;

import com.khushi.projects.buildly.dto.project.ProjectResponse;
import com.khushi.projects.buildly.dto.project.ProjectSummaryReposne;
import com.khushi.projects.buildly.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryReposne toProjectSummaryResponse(Project project);

    List<ProjectSummaryReposne> toListOfProjectSummaryResponse(List<Project> projects);

}
