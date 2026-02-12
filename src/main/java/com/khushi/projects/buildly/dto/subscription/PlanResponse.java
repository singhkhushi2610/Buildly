package com.khushi.projects.buildly.dto.subscription;

public record PlanResponse (
        Long id,
        String name,
        String stripePriceId,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Boolean unlimitedAi,
        String price
) {
}
