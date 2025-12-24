package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.subscription.PlanLimitsResponse;
import com.khushi.projects.buildly.dto.subscription.UsageTodayResponse;
import com.khushi.projects.buildly.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfAUser(Long userId) {
        return null;
    }
}
