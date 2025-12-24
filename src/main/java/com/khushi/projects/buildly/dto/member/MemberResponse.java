package com.khushi.projects.buildly.dto.member;

import com.khushi.projects.buildly.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
