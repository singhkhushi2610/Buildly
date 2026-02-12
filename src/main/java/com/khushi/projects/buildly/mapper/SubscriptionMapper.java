package com.khushi.projects.buildly.mapper;


import com.khushi.projects.buildly.dto.subscription.PlanResponse;
import com.khushi.projects.buildly.dto.subscription.SubscriptionResponse;
import com.khushi.projects.buildly.entity.Plan;
import com.khushi.projects.buildly.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponse toSubscriptionResponse(Subscription subscription);
    PlanResponse toPlanResponse(Plan plan);
}
