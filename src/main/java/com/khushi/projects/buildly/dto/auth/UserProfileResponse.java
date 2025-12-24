package com.khushi.projects.buildly.dto.auth;

public record UserProfileResponse (
    Long id,
    String username,
    String name
) {
}
