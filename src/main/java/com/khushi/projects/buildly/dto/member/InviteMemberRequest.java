package com.khushi.projects.buildly.dto.member;

import com.khushi.projects.buildly.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @Email @NotNull @NotBlank String username,
        @NotNull ProjectRole projectRole
) {
}
