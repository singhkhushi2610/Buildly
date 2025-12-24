package com.khushi.projects.buildly.dto.project;

import java.time.Instant;

public record ProjectSummaryReposne (
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
