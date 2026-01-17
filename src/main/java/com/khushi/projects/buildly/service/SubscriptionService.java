package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.subscription.CheckoutRequest;
import com.khushi.projects.buildly.dto.subscription.CheckoutResponse;
import com.khushi.projects.buildly.dto.subscription.PortalResponse;
import com.khushi.projects.buildly.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);
}
