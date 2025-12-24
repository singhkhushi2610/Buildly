package com.khushi.projects.buildly.controller;

import com.khushi.projects.buildly.dto.project.ProjectRequest;
import com.khushi.projects.buildly.dto.project.ProjectResponse;
import com.khushi.projects.buildly.dto.project.ProjectSummaryReposne;
import com.khushi.projects.buildly.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject (@RequestBody @Valid ProjectRequest request) {
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request, userId));
    }

    @GetMapping
    public ResponseEntity<List<ProjectSummaryReposne>> getMyProjects() {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long projectId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjectById(projectId, userId));
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                         @RequestBody @Valid ProjectRequest request) {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(projectId, request, userId));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        Long userId = 1L;
        projectService.softDelete(userId, projectId);
        return ResponseEntity.noContent().build();
    }

}
