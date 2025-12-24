package com.khushi.projects.buildly.dto.project;

import com.khushi.projects.buildly.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse (
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        UserProfileResponse owner
) {
}
