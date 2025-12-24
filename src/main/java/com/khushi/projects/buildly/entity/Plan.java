package com.khushi.projects.buildly.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Plan {


    Long id;

    String name;

    String stripePriceId;
    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews; // Maximum number of previews allowed per plan

    Boolean unlimitedAi; // Unlimited access to LLM, ignore maxTokenPerDay if true
    Boolean active;


}
