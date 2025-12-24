package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.auth.AuthResponse;
import com.khushi.projects.buildly.dto.auth.LoginRequest;
import com.khushi.projects.buildly.dto.auth.SignupRequest;
import com.khushi.projects.buildly.entity.User;
import com.khushi.projects.buildly.error.BadRequestException;
import com.khushi.projects.buildly.mapper.UserMapper;
import com.khushi.projects.buildly.repository.UserRepository;
import com.khushi.projects.buildly.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService  {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
                    throw new BadRequestException("User already exists with username - " + request.username());
                });
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return new AuthResponse("dummy", userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
