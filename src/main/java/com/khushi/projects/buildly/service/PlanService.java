package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
