package com.khushi.projects.buildly.repository;

import com.khushi.projects.buildly.entity.Subscription;
import com.khushi.projects.buildly.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> pastDue);

    boolean existsByStripeSubscriptionId(String subscriptionId);

    Optional<Subscription> findByStripeSubscriptionId(String subscriptionId);
}
