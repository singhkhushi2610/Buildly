package com.khushi.projects.buildly.entity;

import com.khushi.projects.buildly.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    @Enumerated(value = EnumType.STRING)
    SubscriptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "plan_id")
    Plan plan;

    String stripeSubscriptionId;

    Instant currentPeriodStart;
    Instant currentPeriodEnd;

    Boolean cancelAtPeriodEnd;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    Instant deletedAt; // Soft delete
}
