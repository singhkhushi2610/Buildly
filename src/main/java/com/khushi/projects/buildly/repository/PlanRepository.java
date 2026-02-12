package com.khushi.projects.buildly.repository;

import com.khushi.projects.buildly.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByStripePriceId(String id);
}
