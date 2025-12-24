package com.khushi.projects.buildly.dto.member;

import com.khushi.projects.buildly.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest (@NotNull ProjectRole role) {
}
