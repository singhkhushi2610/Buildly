package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.auth.UserProfileResponse;
import com.khushi.projects.buildly.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
