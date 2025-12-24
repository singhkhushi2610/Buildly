package com.khushi.projects.buildly.dto.subscription;

import com.khushi.projects.buildly.entity.Plan;
import com.khushi.projects.buildly.entity.User;
import com.khushi.projects.buildly.enums.SubscriptionStatus;

import java.time.Instant;

public record SubscriptionResponse(
        Long id,
        User user,
        SubscriptionStatus status,
        Plan plan,
        Long tokensUsedThisCycle,
        Instant currentPeriodStart,
        Instant currentPeriodEnd
) {
}
