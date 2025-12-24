package com.khushi.projects.buildly.entity;

import com.khushi.projects.buildly.enums.SubscriptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Subscription {


    Long id;

    User user;

    SubscriptionStatus status;
    Plan plan;
    String stripeCustomerId;
    String StripSubscriptionId;
    Instant currentPeriodStart;

    Instant currentPeriodEnd;

    Boolean cancelAtPeriodEnd;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt; // Soft delete
}
