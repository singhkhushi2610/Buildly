package com.khushi.projects.buildly.dto.subscription;

public record PlanResponse (
        Long id,

        String name,

        String stripePriceId,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreviews, // Maximum number of previews allowed per plan

        Boolean unlimitedAi, // Unlimited access to LLM, ignore maxTokenPerDay if true
        Boolean active
) {
}
