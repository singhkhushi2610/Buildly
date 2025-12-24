package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.project.FileContentResponse;
import com.khushi.projects.buildly.dto.project.FileNode;
import com.khushi.projects.buildly.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getContent(Long projectId, String path, Long userId) {
        return null;
    }
}
