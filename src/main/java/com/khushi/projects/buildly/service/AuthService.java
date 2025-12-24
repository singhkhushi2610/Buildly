package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.auth.AuthResponse;
import com.khushi.projects.buildly.dto.auth.LoginRequest;
import com.khushi.projects.buildly.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
}
