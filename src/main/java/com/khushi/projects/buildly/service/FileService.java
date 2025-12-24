package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.project.FileContentResponse;
import com.khushi.projects.buildly.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getContent(Long projectId, String path, Long userId);
}
