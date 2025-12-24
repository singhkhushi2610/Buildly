package com.khushi.projects.buildly.dto.project;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest (
        @NotBlank String name
) {
}
