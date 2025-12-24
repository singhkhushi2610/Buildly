package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
