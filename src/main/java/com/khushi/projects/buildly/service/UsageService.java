package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.subscription.PlanLimitsResponse;
import com.khushi.projects.buildly.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfAUser(Long userId);
}
