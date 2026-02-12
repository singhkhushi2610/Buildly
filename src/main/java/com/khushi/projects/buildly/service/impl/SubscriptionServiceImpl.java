package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.subscription.SubscriptionResponse;
import com.khushi.projects.buildly.entity.Plan;
import com.khushi.projects.buildly.entity.Subscription;
import com.khushi.projects.buildly.entity.User;
import com.khushi.projects.buildly.enums.SubscriptionStatus;
import com.khushi.projects.buildly.error.ResourceNotFoundException;
import com.khushi.projects.buildly.mapper.SubscriptionMapper;
import com.khushi.projects.buildly.repository.PlanRepository;
import com.khushi.projects.buildly.repository.ProjectMemberRepository;
import com.khushi.projects.buildly.repository.UserRepository;
import com.khushi.projects.buildly.security.AuthUtil;
import com.khushi.projects.buildly.service.SubscriptionService;
import com.khushi.projects.buildly.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final AuthUtil authUtil;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Value("${app.limits.free-tier-projects-allowed}")
    private Integer freeTierProjectsAllowed;

    @Override
    public SubscriptionResponse getCurrentSubscription() {
        Long userId = authUtil.getCurrentUserId();

        var currentSubscription = subscriptionRepository.findByUserIdAndStatusIn(userId, Set.of(
                SubscriptionStatus.PAST_DUE, SubscriptionStatus.ACTIVE, SubscriptionStatus.TRIALING
        )).orElse(new Subscription());

        return subscriptionMapper.toSubscriptionResponse(currentSubscription);
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String customerId, String subscriptionId) {

        log.info("activateSubscription called: userId={}, planId={}, subscriptionId={}",
                userId, planId, subscriptionId);

        boolean exists = subscriptionRepository.existsByStripeSubscriptionId(subscriptionId);
        log.info("existsByStripeSubscriptionId({}) = {}", subscriptionId, exists);
        if(exists) return;

        Plan plan = getPlan(planId);
        User user = getUser(userId);

        Subscription subscription = Subscription.builder()
                .plan(plan)
                .user(user)
                .stripeSubscriptionId(subscriptionId)
                .status(SubscriptionStatus.INCOMPLETE)
                .build();

        subscriptionRepository.save(subscription);
        log.info("Subscription saved for subscriptionId={}", subscriptionId);
    }

    @Override
    public void updateSubscription(String subscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd,
                                   Boolean cancelAtPeriodEnd, Long planId) {
        boolean subscriptionUpdated = false;
        Subscription subscription = getSubscription(subscriptionId);

        if(status != null && !status.equals(subscription.getStatus())) {
            subscription.setStatus(status);
            subscriptionUpdated = true;
        }

        if(periodStart != null && !periodStart.equals(subscription.getCurrentPeriodStart())) {
            subscription.setCurrentPeriodStart(periodStart);
            subscriptionUpdated = true;
        }

        if(periodEnd != null && !periodEnd.equals(subscription.getCurrentPeriodEnd())) {
            subscription.setCurrentPeriodEnd(periodEnd);
            subscriptionUpdated = true;
        }

        if(cancelAtPeriodEnd != null && !cancelAtPeriodEnd.equals(subscription.getCancelAtPeriodEnd())) {
            subscription.setCancelAtPeriodEnd(cancelAtPeriodEnd);
            subscriptionUpdated = true;
        }

        if(planId != null && !subscription.getPlan().getId().equals(planId)) {
            Plan newPlan = getPlan(planId);
            subscriptionUpdated = true;
        }

        if(subscriptionUpdated) {
            log.debug("Subscription has been updated, subscriptionId - {}", subscriptionId);
        }
    }

    @Override
    public void cancelSubscription(String subscriptionId) {
        Subscription subscription = getSubscription(subscriptionId);
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void renewSubscription(String subscriptionId, Instant periodStart, Instant periodEnd) {
        Subscription subscription = getSubscription(subscriptionId);

        Instant newPeriodStart = periodStart == null ? subscription.getCurrentPeriodEnd() : periodStart;
        subscription.setCurrentPeriodStart(newPeriodStart);
        subscription.setCurrentPeriodEnd(periodEnd);

        if(subscription.getStatus() == SubscriptionStatus.PAST_DUE || subscription.getStatus() == SubscriptionStatus.INCOMPLETE)
            subscription.setStatus(SubscriptionStatus.ACTIVE);

        subscriptionRepository.save(subscription);
    }

    @Override
    public void markSubscriptionPastDue(String subscriptionId) {
        Subscription subscription = getSubscription(subscriptionId);

        if(subscription.getStatus() == SubscriptionStatus.PAST_DUE) {
            log.debug("Subscription is already PAST_DUE, subscriptionId - {}", subscriptionId);
            return;
        }

        subscription.setStatus(SubscriptionStatus.PAST_DUE);
        subscriptionRepository.save(subscription);
    }

    @Override
    public boolean canCreateNewProject() {
        SubscriptionResponse currentSubscription = getCurrentSubscription();
        Long userId = authUtil.getCurrentUserId();
        int countOfOwnedProjects = projectMemberRepository.countOfProjectsOwnedByUser(userId);

        if(currentSubscription.plan() == null)
            return countOfOwnedProjects < freeTierProjectsAllowed;

        return countOfOwnedProjects < currentSubscription.plan().maxProjects();
    }

    // Utility methods

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(userId.toString(), "User")
        );
    }

    private Plan getPlan(Long planId) {
        return planRepository.findById(planId).orElseThrow(
                () -> new ResourceNotFoundException(planId.toString(), "Plan")
        );
    }

    private Subscription getSubscription(String subscriptionId) {
        return subscriptionRepository.findByStripeSubscriptionId(subscriptionId).orElseThrow(
                () -> new ResourceNotFoundException(subscriptionId.toString(), "Subscription")
        );
    }

}
