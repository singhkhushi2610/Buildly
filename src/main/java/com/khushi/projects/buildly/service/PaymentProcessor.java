package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.subscription.CheckoutRequest;
import com.khushi.projects.buildly.dto.subscription.CheckoutResponse;
import com.khushi.projects.buildly.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;

import java.util.Map;

public interface PaymentProcessor {

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal(Long userId);

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
