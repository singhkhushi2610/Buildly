package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.subscription.PlanResponse;
import com.khushi.projects.buildly.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
