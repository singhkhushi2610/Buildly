package com.khushi.projects.buildly.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
