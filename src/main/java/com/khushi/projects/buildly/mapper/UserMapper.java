package com.khushi.projects.buildly.mapper;

import com.khushi.projects.buildly.dto.auth.SignupRequest;
import com.khushi.projects.buildly.dto.auth.UserProfileResponse;
import com.khushi.projects.buildly.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(SignupRequest signupRequest);
    UserProfileResponse toUserProfileResponse(User user);
}
