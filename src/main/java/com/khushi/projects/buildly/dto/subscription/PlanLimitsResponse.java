package com.khushi.projects.buildly.dto.subscription;

public record PlanLimitsResponse(
        String planeName,
        Integer maxTokensPerDay,
        Integer maxProjects,
        boolean unlimitedAi
) {
}
